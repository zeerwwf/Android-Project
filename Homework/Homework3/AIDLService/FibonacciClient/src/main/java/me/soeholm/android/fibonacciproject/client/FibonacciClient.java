package me.soeholm.android.fibonacciproject.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.soeholm.android.fibonacciproject.common.FibonacciRequest;
import me.soeholm.android.fibonacciproject.common.FibonacciResponse;
import me.soeholm.android.fibonacciproject.common.IFibonacciService;

public class FibonacciClient extends Activity implements ServiceConnection, OnClickListener {
    private static final String LOG_TAG = "FibonacciClient";

    private IFibonacciService service;
    private Button btnGogo;
    private TextView txtTitle;
    private TextView txtSubtitle;
    private TextView txtN;
    private LinearLayout layTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "Client created!");

        setContentView(R.layout.activity_fibonacci_client);

        this.txtTitle = (TextView)super.findViewById(R.id.txtTitle);
        this.txtSubtitle = (TextView)super.findViewById(R.id.txtSubtitle);
        this.txtN = (TextView)super.findViewById(R.id.txtN);
        this.layTimes = (LinearLayout)super.findViewById(R.id.layTimes);

        this.btnGogo = (Button)super.findViewById(R.id.btnGogo);
        this.btnGogo.setOnClickListener(this);

        TextView btnDecreaseN = (Button)super.findViewById(R.id.btnDecreaseN);
        btnDecreaseN.setOnClickListener(new AdjustNHandler(false, btnDecreaseN));
        TextView btnIncreaseN = (Button)super.findViewById(R.id.btnIncreaseN);
        btnIncreaseN.setOnClickListener(new AdjustNHandler(true, btnDecreaseN));
    }

    @Override
    protected void onPause() {
        super.onPause();
        super.unbindService(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.bindService(new Intent(IFibonacciService.class.getName()), this, BIND_AUTO_CREATE);
    }


    // ServiceConnection overrides

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(LOG_TAG, "Connected to service successfully");
        this.service = IFibonacciService.Stub.asInterface(iBinder);
        this.btnGogo.setEnabled(true);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(LOG_TAG, "Disconnected from service");
        this.service = null;
        this.btnGogo.setEnabled(false);
    }


    // Handler for main calculation button
    public void onClick(View view) {
        Log.i(LOG_TAG, "Calculation initiated");
        this.btnGogo.setEnabled(false);
        this.layTimes.removeAllViews();
        this.txtSubtitle.setText(getResources().getString(R.string.timings_subtitle_text));

        CharSequence strN = this.txtN.getText();

        for(FibonacciRequest.Type type : FibonacciRequest.Type.values()) {
            new AsyncTask<Object, Void, FibonacciResponse>() {
                FibonacciRequest.Type type;

                @Override
                protected FibonacciResponse doInBackground(Object... input) {
                    FibonacciResponse response = null;
                    this.type = (FibonacciRequest.Type)input[1];

                    try {
                        long n = Long.parseLong(input[0].toString());
                        response = service.fibonacci(new FibonacciRequest(n, this.type));
                    } catch (RemoteException ex) {
                        Log.e(LOG_TAG, "Error on RPCC");
                    } catch (NumberFormatException ex) {
                        Log.e(LOG_TAG, "Malformed 'n' value received");
                    }

                    return response;
                }

                @Override
                protected void onPostExecute(FibonacciResponse response) {
                    if(response != null) {
                        String rFormat = getResources().getString(R.string.result_text);
                        String strResult = String.format(rFormat, response.getResult());
                        FibonacciClient.this.txtTitle.setText(strResult);

                        String strType;
                        switch(this.type) {
                            case ITER_JAVA:
                                strType = getResources().getString(R.string.ITER_JAVA);
                                break;
                            case RECS_JAVA:
                                strType = getResources().getString(R.string.RECS_JAVA);
                                break;
                            case ITER_NATIVE:
                                strType = getResources().getString(R.string.ITER_NATIVE);
                                break;
                            case RECS_NATIVE:
                                strType = getResources().getString(R.string.RECS_NATIVE);
                                break;
                            default:
                                strType = getResources().getString(R.string.unknown_timing_type);
                        }

                        String tFormat = getResources().getString(R.string.timing_text);
                        String strTime = String.format(tFormat, strType, response.getTime());
                        TextView txtRes = new TextView(FibonacciClient.this);
                        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        lParams.gravity = Gravity.CENTER_HORIZONTAL;
                        txtRes.setLayoutParams(lParams);
                        txtRes.setText(strTime);

                        FibonacciClient.this.layTimes.addView(txtRes);
                    } else {
                        FibonacciClient.this.txtTitle.setText(getResources().getString(R.string.error_title_text));
                        FibonacciClient.this.txtSubtitle.setText(getResources().getString(R.string.error_subtitle_text));
                    }
                    FibonacciClient.this.btnGogo.setEnabled(true);
                }
            }.execute(strN, type);
        }
    }


    // Handler for Increase(>)/Decrease(<) buttons
    private class AdjustNHandler implements OnClickListener {
        boolean isIncrease;
        TextView btnDecreaseN;

        private AdjustNHandler(boolean isIncrease, TextView btnDecreaseN) {
            this.isIncrease = isIncrease;
            this.btnDecreaseN = btnDecreaseN;
        }

        @Override
        public void onClick(View view) {
            long n = Long.parseLong(FibonacciClient.this.txtN.getText().toString());
            if(isIncrease) {
                FibonacciClient.this.txtN.setText(Long.toString(n + 1));
                // Leaving minimum, enable decrease button
                if(n == 1) {
                    this.btnDecreaseN.setEnabled(true);
                }
            } else {
                FibonacciClient.this.txtN.setText(Long.toString(n - 1));
                // Minimum reached, disable decrease button
                if(n == 2) {
                    this.btnDecreaseN.setEnabled(false);
                }
            }
        }
    }
}
