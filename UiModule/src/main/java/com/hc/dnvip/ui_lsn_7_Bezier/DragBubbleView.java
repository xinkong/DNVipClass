package com.hc.dnvip.ui_lsn_7_Bezier;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.hc.dnvip.R;

/**
 * 1.气泡静止状态
 * 2.气泡连接状态
 * 3.气泡分离状态
 * 4.气泡消失状态
 * 5.气泡还原动画
 */
public class DragBubbleView extends View {

    /**
     * 静止状态
     */
    private final int STATE_STATIC = 0;
    /**
     * 连接状态
     */
    private final int STATE_CONNECT = 1;
    /**
     * 分离状态
     */
    private final int STATE_SEPARATE = 2;
    /**
     * 消失状态
     */
    private final int STATE_DISAPPEAR = 3;

    /**
     * 当前气泡状态
     */
    private int mStateType = STATE_STATIC;

    /**
     * 当前气泡的画笔
     */
    private Paint mPaint;

    /**
     * 需要绘制的颜色
     */
    private int mDrawColor;

    /**
     * 需要绘制的文字
     */
    private String mHintMsg = "22";
    /**
     * 需要绘制文字的信息
     */
    private Rect mHintMsgSize;

    private float mHintMsgTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());
    /**
     * 气泡距离大圆半径多少倍处于分离状态
     */
    private int mSeparateDistance = 8;

    /**
     * 大圆的中心点
     */
    private PointF mBigCircleCenter;

    /**
     * 大圆半径
     */
    private float mBigCircleRadius =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());;
    /**
     * 小圆的中心点
     */
    private PointF mSmallCircleCenter;

    /**
     * 小圆半径
     */
    private float mSmallCircleRadius;

    /**
     * 两个圆的圆心距
     */
    private float mCenterDistance;


    public DragBubbleView(Context context) {
        this(context, null);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DragBubbleView);

        mDrawColor = array.getColor(R.styleable.DragBubbleView_DragBubble_Color, Color.RED);
        mSmallCircleRadius = array.getDimension(R.styleable.DragBubbleView_DragBubbleSmallRadio, 10);

        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mDrawColor);
        mPaint.setStyle(Paint.Style.FILL);


        mHintMsgSize = new Rect();
        mPaint.setTextSize(mHintMsgTextSize);
        mPaint.getTextBounds(mHintMsg,0,mHintMsg.length(),mHintMsgSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSmallCircleCenter = new PointF(w / 2, h / 2);
        mBigCircleCenter = new PointF(w / 2, h / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //  1.气泡静止状态
        //  2.气泡连接状态
        //  3.气泡分离状态
        //  4.气泡消失状态
        //  5.气泡还原动画


        //画大圆,提示文字
        if(mStateType == STATE_STATIC || mStateType == STATE_SEPARATE) {
            drawBigCircle(canvas);
        }

        if(mStateType == STATE_CONNECT){//画大圆小圆连接线

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mBigCircleCenter.x = event.getX();
                mBigCircleCenter.y = event.getY();
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:

                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 绘制大圆
     * @param canvas
     */
    private void drawBigCircle(Canvas canvas) {
        mPaint.setColor(mDrawColor);
        canvas.drawCircle(mBigCircleCenter.x, mBigCircleCenter.y,mBigCircleRadius,mPaint);
        mPaint.setColor(0xff000000);
        mPaint.setTextSize(mHintMsgTextSize);
        canvas.drawText(mHintMsg,mBigCircleCenter.x-mHintMsgSize.width()/2,mBigCircleCenter.y+mHintMsgSize.height()/2,mPaint);
    }
}
