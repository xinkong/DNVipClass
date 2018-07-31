package com.hc.dnvip.ui_lsn_10_event;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hc.dnvip.R;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new MyView(this));
        setContentView(R.layout.activity_event);
        Activity a;
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    //    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
//        Log.i("tag","我走Activity中的dispatchTouchEvent");
//        return true;
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("tag","我走Activity中的onTouchEvent");
//        return super.onTouchEvent(event);
//    }
}
