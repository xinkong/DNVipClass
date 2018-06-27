package com.hc.dnvip.ui_lsn_4_paint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hc.dnvip.R;

//使用xfermode绘制水波纹效果
public class XfermodeView extends View {

    private Paint mPaint;
    private Bitmap mBitmapDst, mBitmapSrc;
    private int mCurrentLenght = 0;

    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.mipmap.circle_shape);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.wav);

        startAnim();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //设置圆的大小为控件的大小
        setMeasuredDimension(mBitmapDst.getWidth(), mBitmapDst.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmapSrc,  new Rect(mCurrentLenght, 0, mCurrentLenght + mBitmapDst.getWidth(), mBitmapDst.getHeight())
                ,new RectF(0, 0, mBitmapDst.getWidth(), mBitmapDst.getHeight()), mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitmapDst,0,0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mBitmapSrc.getWidth(),0);
        valueAnimator.setDuration(6000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentLenght = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
