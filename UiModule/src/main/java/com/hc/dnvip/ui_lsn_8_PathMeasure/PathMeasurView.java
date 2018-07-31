package com.hc.dnvip.ui_lsn_8_PathMeasure;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hc.dnvip.R;

/**
 * Created by huchao on 2018/7/12.
 */
public class PathMeasurView extends View {

    /**
     * 绘制需要的画笔
     */
    private Paint mPaint;
    /**
     * 需要绘制的图片
     */
    private Bitmap mBitmap;

    /**
     * 获取pos和tan值
     */
    private float mPos[];
    private float mTan[];

    /**
     * 初始化当前位置
     */
    private float mCurrentPosition = 0;

    /**
     * 需要绘制圆的半径
     */
    private float mRaduis = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

    private int mCenterX, mCenterY;

    /**
     * 需要绘制的路径
     */
    private Path mPath;

    /**
     * 需要选中的矩阵
     */
    private Matrix mMatrix;

    public PathMeasurView(Context context) {
        this(context, null);
    }

    public PathMeasurView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasurView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initParams();
    }

    /**
     * 初始化参数
     */
    private void initParams() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow,options);

        mPos = new float[2];
        mTan = new float[2];

        mMatrix = new Matrix();

        startAnim();
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1f);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(3*1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPosition = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //坐标系平移到中心点
        canvas.translate(mCenterX, mCenterY);
        mPath = new Path();
        mPath.addCircle(0, 0, mRaduis, Path.Direction.CCW);
        PathMeasure measure = new PathMeasure(mPath, false);
        measure.getPosTan(measure.getLength()*mCurrentPosition,mPos,mTan);

        float sweepDegrees = (float) (Math.atan2(mTan[1],mTan[0])*180/Math.PI);

        //矩阵充值
        mMatrix.reset();
        //旋转
        mMatrix.postRotate(sweepDegrees,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        //平移
        mMatrix.postTranslate(mPos[0]-mBitmap.getWidth()/2,mPos[1]-mBitmap.getHeight()/2);
        //先画一个圆
        canvas.drawPath(mPath, mPaint);
        canvas.drawBitmap(mBitmap,mMatrix,null);

    }
}
