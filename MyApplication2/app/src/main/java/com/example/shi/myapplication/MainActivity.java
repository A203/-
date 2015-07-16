package com.example.shi.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;


public class MainActivity extends Activity {
    Button btn;
    String str;
    String s;
    boolean flag = false;
    //    private TabHost host;
    PhoneNumberAddressAndAgent zs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                EditText edit =(EditText) findViewById(R.id.et);
                str = edit.getText().toString();
                    flag = false;
                new Thread(){
                    @Override
                    public void run()
                    {
                        try {
                            //System.out.println("dasdadaseefefefefefefeefesdasdasfsdaf");
                            zs = new PhoneNumberAddressAndAgent(str);
                            //System.out.println("dasdadasdasdasdasfsdaf");
                            s =zs.getAddress()+":"+zs.getAgent();
                        } catch (Exception e) {
                            s="世上最遥远的距离就是没网。";
                        }
                        flag = true;

                    }
                }.start();
                    while(!flag){}
                   // System.out.println(s);
                    if(s.equals(null+":"+null)){
                        s = "您输入的号码有误，请重新输入！";
                    }
                    Toast toast=Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT);
                toast.show();
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
