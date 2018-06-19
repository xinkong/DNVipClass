package com.hc.dnvip.ui_lsn_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hc.dnvip.R;

/**
 * Created by huchao on 2018/6/17.
 *  * TileMode 拉伸形式
 * CLAMP --是拉伸最后一个像素铺满
 * MIRROR ---是横向纵向不足处不断翻转镜像平铺
 * REPEAT ---类似电脑壁纸，横向纵向不足的重复放置
 */
public class BitmapShaderView extends View {

    Bitmap mBitmap;
    Paint mPaint;
//    Paint mPaintMirror;
//    Paint mPaintRepeat;

    public BitmapShaderView(Context context) {
        this(context,null);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
//        mPaintMirror  = new Paint();
//        mPaintRepeat = new Paint();
        //获取位图渲染bitmap
        mBitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.xyjy2)).getBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //CLAMP
//        BitmapShader shaderClamp = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        mPaint.setShader(shaderClamp);
//        Rect rect = new Rect(0,0,500,500);
//        canvas.drawRect(rect, mPaint);
//
//        BitmapShader shaderMirror = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
//        mPaint.setShader(shaderMirror);
//        canvas.drawRect(new Rect(550,0,1000,500), mPaint);
//
//        BitmapShader shaderRepeat = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        mPaint.setShader(shaderRepeat);
//        canvas.drawRect(new Rect(0,550,500,1000), mPaint);

        //倒影效果,高是图像的两倍,宽等于图片
        BitmapShader shaderMirror2 = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaint.setShader(shaderMirror2);
        canvas.drawRect(new Rect(0,0,0+mBitmap.getWidth(),0+mBitmap.getHeight()*2), mPaint);
    }
}
