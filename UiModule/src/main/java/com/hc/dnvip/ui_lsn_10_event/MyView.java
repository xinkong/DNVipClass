package com.hc.dnvip.ui_lsn_10_event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by huchao on 2018/7/17.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //请求父容器拦截事件
//       requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean b = super.dispatchTouchEvent(event);

        Log.i("tag","dispatchTouchEvent返回值"+b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("tag", "我调用了ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("tag", "我调用了ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("tag", "我调用了ACTION_UP");
                break;

        }
//        boolean b = super.onTouchEvent(event);
        boolean b = true;
        Log.i("tag","当前返回值是:"+b);
        return b;
    }


}
