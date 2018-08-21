package com.hc.dnvip.ui_study_myself.heart;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class HeartView extends View{

    //需要的画笔
    private Paint mPaint;
    private Paint mTextPaint;
    private Rect mTextBounds = new Rect();
    private String mText = "哈哈";
    //绘制路径
    private Path mPath;
    //随机生成的颜色
    private int[] colors = new int[]{0xFFB95419, 0xFFAA9E18, 0xFF75BD1D, 0xFF891475
            , 0xFFD90CB7, 0xFF880A29, 0xFFF7020E, 0xFFE8030E};

    private float realWidth = 100,realHeight=100;




    public HeartView(Context context) {
        this(context,null);
    }

    public HeartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HeartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200, 200);
    }

    private void initParams() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(colors[new Random().nextInt(colors.length)]);
        mPath = new Path();

        mTextPaint = new Paint();
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(0xff000000);
        mTextPaint.getTextBounds(mText,0,mText.length(),mTextBounds);

        startAnim();

    }

    private void startAnim() {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1f);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f);

//        animatorSet.play(animatorX).with(animatorY).after(animatorAlpha);
        animatorSet.playTogether(animatorX,animatorY,animatorAlpha);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(mLisener!=null){
                    mLisener.onFinish(HeartView.this);
                }
            }
        });
        animatorSet.setDuration(2000);
        animatorSet.start();

//        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 0, 1f);
//        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 0, 1f);
//        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this,  holder2, holder3);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                if(mLisener!=null){
//                    mLisener.onFinish(HeartView.this);
//                }
//            }
//        });
//        animator.setDuration(2000);
//        animator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo((float) (0.5*realWidth), (float) (0.17*realHeight));
        mPath.cubicTo((float) (0.15*realWidth), (float) (-0.35*realHeight), (float) (-0.4*realWidth), (float) (0.45*realHeight), (float) (0.5*realWidth),realHeight);
        mPath.moveTo((float) (0.5*realWidth),realHeight);
        mPath.cubicTo((float) (realWidth+0.4*realWidth), (float) (0.45*realHeight),(float) (realWidth-0.15*realWidth), (float) (-0.35*realHeight),(float) (0.5*realWidth), (float) (0.17*realHeight));
//        mPath.close();
        canvas.drawPath(mPath,mPaint);

        canvas.drawText(mText,realWidth/2-mTextBounds.width()/2,realHeight/2+mTextBounds.height()/2,mTextPaint);

    }

    private OnFinishLisener mLisener;

    public void setOnFinishLisener(OnFinishLisener lisener) {
        mLisener = lisener;
    }

    public interface OnFinishLisener {
        void onFinish(View view);
    }

}
