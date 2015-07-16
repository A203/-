package com.example.x84.zs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.x84.zs.R;


/**
 * Created by X84 on 2015/7/9.
 */
public class CustomView extends Activity {
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.zs_boom);
        /*LinearLayout root = (LinearLayout) findViewById(R.id.root);
        final ZSView draw = new ZSView(this);
        draw.setMinimumWidth(300);
        draw.setMinimumHeight(500);
        root.addView(draw);*/
    }
}
