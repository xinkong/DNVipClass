package com.hc.dnvip.ui_lsn_3;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 渐变渲染/梯度渲染
 */
public class SweepGradientView extends View{

    private int mWidth,mHeight;
    private Paint mPaint;
    private SweepGradient mSweepGradient;

    private Matrix matrix = new Matrix(); // 旋转需要的矩阵
    private int scanSpeed = 5; // 扫描速度
    private int scanAngle; // 扫描旋转的角度

    public SweepGradientView(Context context) {
        this(context,null);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        post(run);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int circleRadius = mWidth / 5 / 2;
        mPaint.setColor(0xFF191819);
        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 5; i++) {
            canvas.drawCircle(mWidth / 2, mWidth / 2, circleRadius + i * circleRadius, mPaint);
        }

        //梯度渲染开始
        // 画布的旋转变换 需要调用save() 和 restore()
        canvas.save();

        mSweepGradient = new SweepGradient(mWidth / 2, mHeight / 2,
                new int[]{Color.TRANSPARENT, Color.parseColor("#84B5CA")}, null);
        mPaint.setShader(mSweepGradient); // 设置着色器
        mPaint.setStyle(Paint.Style.FILL);
        canvas.concat(matrix);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth/2, mPaint);

        canvas.restore();
    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            scanAngle = (scanAngle + scanSpeed) % 360; // 旋转角度 对360取余
            matrix.postRotate(scanSpeed, mWidth / 2, mHeight / 2); // 旋转矩阵
            invalidate(); // 通知view重绘
            postDelayed(run, 50); // 调用自身 重复绘制
        }
    };
}
