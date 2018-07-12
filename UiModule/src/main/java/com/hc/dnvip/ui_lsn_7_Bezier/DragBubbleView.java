package com.hc.dnvip.ui_lsn_7_Bezier;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.MediaCasStateException;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

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
     * 绘制文字的画笔
     */
    private Paint mTextPaint;

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
    private float mBigCircleRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
    ;
    /**
     * 小圆的中心点
     */
    private PointF mSmallCircleCenter;

    /**
     * 小圆半径
     */
    private float mSmallCircleRadius = 10f;

    /**
     * 两个圆的圆心距
     */
    private float mCenterDistance;

    /**
     * 气泡爆炸画笔
     */
    private Paint mBurstPaint;
    /**
     * 气泡爆炸的图片id数组
     */
    private int[] mBurstDrawablesArray = {R.drawable.burst_1, R.drawable.burst_2
            , R.drawable.burst_3, R.drawable.burst_4, R.drawable.burst_5};

    /**
     * 气泡爆炸的bitmap
     */
    private Bitmap[] mBurstBitmapsArray;

    /**
     * 爆炸绘制区域
     */
    private Rect mBurstRect;
    /**
     * 绘制消失的图片序号
     */
    private int mCurDrawableIndex;
    /**
     * 是否在执行气泡爆炸动画
     */
    private boolean mIsBurstAnimStart = false;

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

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(0xff000000);
        mTextPaint.setTextSize(mHintMsgTextSize);
        mTextPaint.setStyle(Paint.Style.FILL);
        mHintMsgSize = new Rect();
        mTextPaint.getTextBounds(mHintMsg, 0, mHintMsg.length(), mHintMsgSize);

        mBurstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBurstPaint.setFilterBitmap(true);
        mBurstRect = new Rect();
        mBurstBitmapsArray = new Bitmap[mBurstDrawablesArray.length];
        for (int i = 0; i < mBurstDrawablesArray.length; i++) {
            //将气泡爆炸的drawable转为bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mBurstDrawablesArray[i]);
            mBurstBitmapsArray[i] = bitmap;
        }
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
        if (mStateType == STATE_STATIC || mStateType == STATE_SEPARATE) {
            drawBigCircle(canvas);
        }

        if (mStateType == STATE_CONNECT) {//画大圆小圆连接线

            //绘制小圆
            canvas.drawCircle(mSmallCircleCenter.x, mSmallCircleCenter.y, mSmallCircleRadius, mPaint);

            //计算出sin cos 的值
            float sinValue = (mBigCircleCenter.y - mSmallCircleCenter.y) / mCenterDistance;
            float cosValue = (mBigCircleCenter.x - mSmallCircleCenter.x) / mCenterDistance;

            //小圆AB点坐标
            float smallAx = mSmallCircleCenter.x - mSmallCircleRadius * sinValue;
            float smallAy = mSmallCircleCenter.y + mSmallCircleRadius * cosValue;

//            canvas.drawText("a", smallAx, smallAy, mTextPaint);

            float smallBx = mSmallCircleCenter.x + mSmallCircleRadius * sinValue;
            float smallBy = mSmallCircleCenter.y - mSmallCircleRadius * cosValue;
//            canvas.drawText("b", smallBx, smallBy, mTextPaint);
            //大圆CD点坐标
            float bigCx = mBigCircleCenter.x - mBigCircleRadius * sinValue;
            float bigCy = mBigCircleCenter.y + mBigCircleRadius * cosValue;
//            canvas.drawText("c", bigCx, bigCy, mTextPaint);

            float bigDx = mBigCircleCenter.x + mBigCircleRadius * sinValue;
            float bigDy = mBigCircleCenter.y - mBigCircleRadius * cosValue;
//            canvas.drawText("d", bigDx, bigDy, mTextPaint);

            //两个圆中心点坐标(锚点坐标)
            float disX = (mBigCircleCenter.x + mSmallCircleCenter.x) / 2;
            float disY = (mBigCircleCenter.y + mSmallCircleCenter.y) / 2;
//            canvas.drawText("z", disX, disY, mTextPaint);

            //绘制曲线
            Path path = new Path();
            path.moveTo(smallAx, smallAy);
            path.quadTo(disX, disY, bigCx, bigCy);
            path.lineTo(bigDx, bigDy);
            path.quadTo(disX, disY, smallBx, smallBy);
            path.close();
            canvas.drawPath(path, mPaint);

            //绘制大圆
            drawBigCircle(canvas);
        }

        if (mStateType == STATE_DISAPPEAR && mIsBurstAnimStart) {//消失
            mBurstRect.set((int) (mBigCircleCenter.x - mBigCircleRadius),
                    (int) (mBigCircleCenter.y - mBigCircleRadius),
                    (int) (mBigCircleCenter.x + mBigCircleRadius),
                    (int) (mBigCircleCenter.y + mBigCircleRadius));

            canvas.drawBitmap(mBurstBitmapsArray[mCurDrawableIndex], null,
                    mBurstRect, mBurstPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mStateType == STATE_STATIC) {

                    mBigCircleCenter.x = event.getX();
                    mBigCircleCenter.y = event.getY();
                    //计算圆心距:
                    mCenterDistance = (float) Math.hypot(mBigCircleCenter.x - mSmallCircleCenter.x, mBigCircleCenter.y - mSmallCircleCenter.y);
                    if (mCenterDistance <= mSeparateDistance * mBigCircleRadius) {//处于连接状态,为后面移动铺路
                        mStateType = STATE_CONNECT;
                    } else {//超过最大距离,在按下屏幕时应该是静止状态(认为没有点在目标上)
                        mStateType = STATE_STATIC;
                    }
                }

                //思路2:判断当前点击的xy是否在大圆范围内

                //思路3


                break;

            case MotionEvent.ACTION_MOVE:

                if (mStateType != STATE_STATIC && mStateType != STATE_DISAPPEAR) {
                    mBigCircleCenter.x = event.getX();
                    mBigCircleCenter.y = event.getY();
                    //计算圆心距:
                    mCenterDistance = (float) Math.hypot(mBigCircleCenter.x - mSmallCircleCenter.x, mBigCircleCenter.y - mSmallCircleCenter.y);
                    if (mCenterDistance <= mSeparateDistance * mBigCircleRadius && mStateType != STATE_SEPARATE) {
                        mStateType = STATE_CONNECT;
                    } else {//分离
                        mStateType = STATE_SEPARATE;
                    }

                    invalidate();
                }


                break;

            case MotionEvent.ACTION_UP:
                if (mStateType == STATE_CONNECT) {
                    //回弹效果
                    startBubbleRestAnim();
                } else if (mStateType == STATE_SEPARATE) {
                    float x = event.getX();
                    float y = event.getY();
                    if(Math.hypot(x - mSmallCircleCenter.x, y - mSmallCircleCenter.y)<mSeparateDistance * mBigCircleRadius ){
                        mBigCircleCenter.x = x;
                        mBigCircleCenter.y = y;
                        mStateType = STATE_CONNECT;
                        startBubbleRestAnim();
                    }else {
                        mStateType = STATE_DISAPPEAR;
                        //执行消失动画
                        startBubbleBurstAnim();
                    }
                }

                break;
        }

        return true;
    }

    private void startBubbleBurstAnim() {
        //气泡改为消失状态
        mIsBurstAnimStart = true;
        //做一个int型属性动画，从0~mBurstDrawablesArray.length结束
        ValueAnimator anim = ValueAnimator.ofInt(0, mBurstDrawablesArray.length);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(500);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //设置当前绘制的爆炸图片index
                mCurDrawableIndex = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //修改动画执行标志
                mIsBurstAnimStart = false;
            }
        });
        anim.start();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startBubbleRestAnim() {
        ValueAnimator anim = ValueAnimator.ofObject(new PointFEvaluator(),
                new PointF(mBigCircleCenter.x, mBigCircleCenter.y),
                new PointF(mSmallCircleCenter.x, mSmallCircleCenter.y));

        anim.setDuration(200);
        anim.setInterpolator(new OvershootInterpolator(5f));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBigCircleCenter = (PointF) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mStateType = STATE_STATIC;
            }
        });
        anim.start();
    }

    /**
     * 绘制大圆
     *
     * @param canvas
     */
    private void drawBigCircle(Canvas canvas) {
        mPaint.setColor(mDrawColor);
        canvas.drawCircle(mBigCircleCenter.x, mBigCircleCenter.y, mBigCircleRadius, mPaint);

        canvas.drawText(mHintMsg, mBigCircleCenter.x - mHintMsgSize.width() / 2, mBigCircleCenter.y + mHintMsgSize.height() / 2, mTextPaint);
    }
}
