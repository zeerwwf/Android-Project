package me.soeholm.android.fibonacciproject.fibonacciservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FibonacciService extends Service {
    private static final String LOG_TAG = "FibonacciService";
    private IFibonacciServiceImpl service;

    @Override
    public void onCreate() {
        Log.i(LOG_TAG, "Service created.");
        super.onCreate();
        this.service = new IFibonacciServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "Client bound to service!");
        return this.service;
    }

    @Override
    public void onDestroy() {
        Log.i(LOG_TAG, "Service destroyed.");
        this.service = null;
        super.onDestroy();
    }
}
