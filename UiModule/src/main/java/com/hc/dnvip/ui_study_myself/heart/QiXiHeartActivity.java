package com.hc.dnvip.ui_study_myself.heart;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hc.dnvip.R;

public class QiXiHeartActivity extends AppCompatActivity {

    HeartView mHeartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qi_xi_heart);
//        mHeartView = new HeartView(this);
//        setContentView(mHeartView);
//        startPropertyValueHolderAnim(mHeartView);
//        mHeartView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    public void startPropertyValueHolderAnim(View v) {
//        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 0, 1f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 0, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v,  holder2, holder3);
        animator.setDuration(2000);
        animator.start();

    }
}
