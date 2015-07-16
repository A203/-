package org.run;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import GPSInfo.GPSListener;
import GPSInfo.GPSPoint;


public class MainActivity extends ActionBarActivity {
    boolean flag = true;
    Button button;
    MainActivity mainActivity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("111111111","222222");
        super.onCreate(savedInstanceState);
        Log.i("111111111", "222222333333333333333333333333333333333");
        setContentView(R.layout.activity_main);
        Log.i("111111111", "222222333333333333333333333333333333333444444444444444444444444444");
        (new Thread(){
            @Override
            public void run() {
                Log.i("111111111", "2222223333333333333333333333333333333334444444444444444444444444444555555555555555555555555555");
                GPSListener gpsListener = new GPSListener(mainActivity);
                GPSPoint gpsPoint;
                Log.i("111111111", "222222333333333333333333333333333333333444444444444444444444444444455555555555555555555555555566666666666666666666");
                while (true) {
                    //Log.i("111111111", "22222233333333333333333333333333333333344444444444444444444444444445555555555555555555555555556666666666666666666677777777777777777777777777");
                    if(flag) {
                        Log.i("111111111", "2222223333333333333333333333333333333334444444444444444444444444444555555555555555555555555555666666888888888888888888888888888888888888");

                        gpsPoint = gpsListener.getGPSPoint();
                        Log.i("111111111", "2222223333333333333333333333333333333334444444444444444444444444444555555555555555555555555555666600000000000000000000000000000000000000000");
                        Log.e("Date", gpsPoint.getDate().toString());
                        Log.e("Latitude:", (new Float(gpsPoint.getLatitude())).toString());
                        Log.e("Longitude:", (new Float(gpsPoint.getLongitude())).toString());
                        Log.e("Speed", (new Float(gpsPoint.getSpeed())).toString());
                        Log.i("111111111", "2222223333333333333333333333333333333334444444444444444444444444444555555555555555555555555555666666888899999999999999999999999999999999999");

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
       button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=!flag;
                Log.i("1234567890",(new Boolean(flag)).toString());
            }
        });
    }

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
