package com.hc.dnvip.ui_lsn_2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

public class WaterfallFLowLayout extends ViewGroup {

    //判断是不是第一次进入
    private boolean mIsFirst = true;

    private int mWidth = 0;
    private int mHeight = 0;

    public WaterfallFLowLayout(Context context) {
        super(context);
    }

    public WaterfallFLowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterfallFLowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mIsFirst) {
            mIsFirst = false;
            return;
        }
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //宽度不用计算,取父布局的宽度即可
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        if(heightMode == MeasureSpec.EXACTLY){

        }else if(heightMode == MeasureSpec.AT_MOST){
            //计算高度
            for (int i = 0; i < getChildCount(); i++) {

            }
        }

        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    private void oneLineView(){

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
