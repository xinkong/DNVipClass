package com.hc.dnvip.ui_lsn_18_drawerLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huchao on 2018/8/2.
 * 绘制背景
 */
public class MenuBgView extends View {

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 背景颜色
     */
    private int mColor;
    /**
     * 需要绘制的路径
     */
    private Path mPath;


    public MenuBgView(Context context) {
        this(context,null);
    }

    public MenuBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MenuBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void  init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mColor);
        canvas.drawPath(mPath,mPaint);
    }

    //设置当前的y值和背景颜色
    public void setYPoint(float yPoint,float percent){
        //设置背景颜色
        //起始点,x默认取0y取高度的1/8
        float xStart = 0,yStart = -getHeight()/8;
        //控制点
        float mControlX = getWidth()*percent*3/2;
        //结束点
        //起始点,x默认取0y取高度的1/8
        float xEnd = 0,yEnd = getHeight()+getHeight()/8;
        mPath.reset();
        mPath.moveTo(xStart,yStart);
        mPath.quadTo(mControlX,yPoint,xEnd,yEnd);
        mPath.close();
        invalidate();
    }

    public void setColor(int color){
        mColor = color;
    }

}
