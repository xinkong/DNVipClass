package com.hc.dnvip.ui_lsn_12_loadAnim;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hc.dnvip.R;

/**
 * 加载动画anim
 */
public class LoadAnimActivity extends AppCompatActivity {

    LoadingView mloadingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_anim);
        mloadingview = findViewById(R.id.loadingview);
//        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(LoadAnimActivity.this,"hehe",Toast.LENGTH_SHORT).show();
//            }
//        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mloadingview.loadFinish();
            }
        },5000);
    }
}
