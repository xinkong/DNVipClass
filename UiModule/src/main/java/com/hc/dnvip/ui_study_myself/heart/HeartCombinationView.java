package com.hc.dnvip.ui_study_myself.heart;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 组合
 */
public class HeartCombinationView extends ViewGroup {

    List<Point> mPoints = new ArrayList<>();
    List<HeartView> mHeartViews = new ArrayList<>();
    private Context mContext;

    public HeartCombinationView(Context context) {
        super(context);
    }

    public HeartCombinationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    public HeartCombinationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createHeart(30);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void layoutChild() {
        for (int i = 0; i < mPoints.size(); i++) {
            Point p = mPoints.get(i);
            HeartView heartView = mHeartViews.get(i);
            heartView.layout(p.x, p.y, (p.x + 200), (p.y + 200));
        }
    }

    int mX ,mY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                post(new Runnable() {
                    @Override
                    public void run() {
                        HeartView heartView = new HeartView(mContext);
                        addView(heartView);
                        heartView.setOnFinishLisener(new HeartView.OnFinishLisener() {
                            @Override
                            public void onFinish(View view) {
                                removeView(view);
                            }
                        });
                        //判断下间距
                        if(Math.abs(mX-x)>50 || Math.abs(mY-y)>50){
                            mX = x;
                            mY = y;
                            heartView.layout(x, y, x + 200, y + 200);
                        }
                    }
                });
                break;

            case MotionEvent.ACTION_UP:
                break;
        }


        return true;

    }


    private void createOneHeart() {
        int x = new Random().nextInt(getWidth());
        int y = new Random().nextInt(getHeight());
        Point point = new Point(x, y);
        mPoints.add(point);
        HeartView heartView = new HeartView(mContext);
        mHeartViews.add(heartView);
        addView(heartView);
        requestLayout();
    }

    public void createHeart(int num) {
        for (int i = 0; i < num; i++) {
            int x = new Random().nextInt(getWidth());
            int y = new Random().nextInt(getHeight());
            Point point = new Point(x, y);
            mPoints.add(point);
            HeartView heartView = new HeartView(mContext);
            mHeartViews.add(heartView);
            addView(heartView);
            layoutChild();
//            requestLayout();
        }
        createOrDismiss();

    }

    private void createOrDismiss() {
        postDelayed(new CreateOrDis(), 1500);
//        post(new CreateOrDis());
    }

    private class CreateOrDis implements Runnable{
        @Override
        public void run() {
//            Log.i("tag","createOrDismiss");
            //去除10个在生成10个
            for (int i = 0; i < new Random().nextInt(10)+4; i++) {
                removeView(mHeartViews.get(i));
                mPoints.remove(i);
                mHeartViews.remove(i);
                createOneHeart();
                layoutChild();
            }
            postDelayed(this,new Random().nextInt(1500));
        }
    }
}
