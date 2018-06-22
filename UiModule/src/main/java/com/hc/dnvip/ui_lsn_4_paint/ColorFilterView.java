package com.hc.dnvip.ui_lsn_4_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hc.dnvip.R;

public class ColorFilterView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    private int mWidth,mHeight;

    public ColorFilterView(Context context) {
        this(context,null);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    private void init() {
        mPaint  = new Paint();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xyjy2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //原图
        canvas.drawBitmap(mBitmap,mWidth/2-mBitmap.getWidth()/2,0,null);
        //android 提供的基础颜色通道过滤矩阵 RGBA 变换
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0
        });

        //美白效果(即色彩变亮即可)
        ColorMatrixColorFilter colorFilter1 = new ColorMatrixColorFilter(new float[]{
                1.3f,0,0,0,0,
                0,1.3f,0,0,0,
                0,0,1.3f,0,0,
                0,0,0,1,0
        });
        mPaint.setColorFilter(colorFilter1);
        canvas.drawBitmap(mBitmap,0,mBitmap.getHeight()+20,mPaint);

        //底片效果(即所以颜色取反,并和255值)
        ColorMatrixColorFilter colorFilter2 = new ColorMatrixColorFilter(new float[]{
                -1,0,0,0,255,
                0,-1,0,0,255,
                0,0,-1,0,255,
                0,0,0,1,0
        });
        mPaint.setColorFilter(colorFilter2);
        canvas.drawBitmap(mBitmap,mBitmap.getWidth()+20,mBitmap.getHeight()+20,mPaint);

        // 黑白照片
        //是将我们的三通道变为单通道的灰度模式
        // 去色原理：只要把R G B 三通道的色彩信息设置成一样，那么图像就会变成灰色，
        // 同时为了保证图像亮度不变，同一个通道里的R+G+B =1
        ColorMatrixColorFilter colorFilter3 = new ColorMatrixColorFilter(new float[]{
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0,0,0,1,0,
        });
        mPaint.setColorFilter(colorFilter3);
        canvas.drawBitmap(mBitmap,mBitmap.getWidth()*2+40,mBitmap.getHeight()+20,mPaint);

        //复古效果
        ColorMatrixColorFilter colorMartrix4 = new ColorMatrixColorFilter(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f, 1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0,
        });
        mPaint.setColorFilter(colorMartrix4);
        canvas.drawBitmap(mBitmap,mBitmap.getWidth()*3+60,mBitmap.getHeight()+20,mPaint);
    }
}
