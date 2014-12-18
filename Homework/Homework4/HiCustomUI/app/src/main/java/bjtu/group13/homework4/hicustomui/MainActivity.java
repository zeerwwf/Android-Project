package bjtu.group13.homework4.hicustomui;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //attributes
    private ImageButton button4 = null;
    private ImageButton button5 = null;
    private ImageButton button6 = null;
    private ImageButton button7 = null;
    private ImageButton button8 = null;
    private ImageButton button9 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize attributes
        button4 = (ImageButton)findViewById(R.id.imageButton4);
        button5 = (ImageButton)findViewById(R.id.imageButton5);
        button6 = (ImageButton)findViewById(R.id.imageButton6);
        button7 = (ImageButton)findViewById(R.id.imageButton7);
        button8 = (ImageButton)findViewById(R.id.imageButton8);
        button9 = (ImageButton)findViewById(R.id.imageButton9);
        button4.setOnClickListener(listen1);
        button5.setOnClickListener(listen1);
        button6.setOnClickListener(listen1);
        button7.setOnClickListener(listen1);
        button8.setOnClickListener(listen1);
        button9.setOnClickListener(listen1);

    }


    private View.OnClickListener listen1 = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            switch(view.getId()) {

                case R.id.imageButton4:
                    Toast.makeText(MainActivity.this, "Start Internet Explorer", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageButton5:
                    Toast.makeText(MainActivity.this, "Start Facebook", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageButton6:
                    Toast.makeText(MainActivity.this, "Start iTunes", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageButton7:
                    Toast.makeText(MainActivity.this, "Start Alarm", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageButton8:
                    Toast.makeText(MainActivity.this, "Start WeChat", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageButton9:
                    Toast.makeText(MainActivity.this, "Start Camera", Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
