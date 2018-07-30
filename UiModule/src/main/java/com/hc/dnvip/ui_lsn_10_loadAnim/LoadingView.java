package com.hc.dnvip.ui_lsn_10_loadAnim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public class LoadingView extends View {

    private ValueAnimator mAnimator;
    private Paint mPaint;
    private int[] mColors = new int[]{0xffff0000, 0xff00ff00, 0xff0000ff, 0xffff0000, 0xff00ff00, 0xff0000ff};
    private float mPointRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
    private float mRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
    //大圆转动的角度
    private float mCircleAngle = 0;
    private PointF mConterPoint;
    //扩展View的最大半径
    private float mExtedRadius;
    private float mExtedCircleRadius = 0;
    private DrawAnim mDrawAnim;


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mConterPoint = new PointF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mConterPoint.x = w / 2;
        mConterPoint.y = h / 2;
        mExtedRadius = (float) Math.sqrt(w / 2 * w / 2 + h / 2 * h / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mDrawAnim == null) {
            mDrawAnim = new RotateAnim();
        }
        mDrawAnim.draw(canvas);
    }

    //结束
    public void loadFinish() {
        if (mDrawAnim instanceof RotateAnim) {
            //停止动画
            mDrawAnim.cancel();
            //收缩动画
            mDrawAnim = new ShrinkAnim();
        }
    }

    //使用策略模式构建(状态模式)
    private abstract class DrawAnim {
        public abstract void draw(Canvas canvas);

        public void cancel() {
            if (mAnimator != null) {
                mAnimator.cancel();
            }
        }
    }


    /**
     * 旋转动画
     */
    private class RotateAnim extends DrawAnim {
        public RotateAnim() {
            //逆时针
//            mAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));
            //顺时针
            mAnimator = ValueAnimator.ofFloat((float) (Math.PI * 2), 0);
            mAnimator.setDuration(1500);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限制循环
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCircleAngle = (float) animation.getAnimatedValue();
                    //View重绘
//                    invalidate();
                    postInvalidate();
                }
            });
            mAnimator.start();
//            mAnimator.reverse();
        }

        @Override
        public void draw(Canvas canvas) {

            //绘制背景
            drawBg(canvas);
            //绘制外围圆环
            drawCircle(canvas);


        }
    }

    private void drawCircle(Canvas canvas) {
        //计算一个圆应该分成多少分
        float oneAngle = (float) (Math.PI * 2 * 1.0f / mColors.length);
        //画出外围圆环
        for (int i = 0; i < mColors.length; i++) {
            mPaint.setColor(mColors[i]);
            float x = (float) (mConterPoint.x + mRadius * Math.sin(i * oneAngle + mCircleAngle));
            float y = (float) (mConterPoint.y + mRadius * Math.cos(i * oneAngle + mCircleAngle));
            canvas.drawCircle(x, y, mPointRadius, mPaint);
        }
    }

    //绘制背景
    private void drawBg(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
    }

    /**
     * 收缩动画
     */
    private class ShrinkAnim extends DrawAnim {
        public ShrinkAnim() {
            mAnimator = ValueAnimator.ofFloat(0, mRadius);
//            mAnimator = ValueAnimator.ofFloat(mRadius,0);
            mAnimator.setDuration(1200);
            mAnimator.setInterpolator(new OvershootInterpolator(10f));
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRadius = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //切换扩展动画
                    mDrawAnim.cancel();
                    mDrawAnim = new ExtendAnim();
                }
            });
//            mAnimator.start();
            mAnimator.reverse();
        }

        @Override
        public void draw(Canvas canvas) {
            drawBg(canvas);
            //绘制圆
            drawCircle(canvas);
        }
    }

    /**
     * 扩展动画
     */
    private class ExtendAnim extends DrawAnim {

        public ExtendAnim() {
            mAnimator = ValueAnimator.ofFloat(0, mExtedRadius);
            mAnimator.setDuration(1200);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mExtedCircleRadius = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mAnimator.start();
        }

        @Override
        public void draw(Canvas canvas) {

            mPaint.setColor(Color.WHITE);
            float strokeWidth = mExtedRadius - mExtedCircleRadius;
            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setStyle(Paint.Style.STROKE);
            //画圆的半径 = 空心圆的半径 + 画笔的宽度/2
            float radius = mExtedCircleRadius + strokeWidth / 2;
            canvas.drawCircle(mConterPoint.x, mConterPoint.y, radius, mPaint);

        }
    }

}
