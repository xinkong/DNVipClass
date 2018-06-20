package com.hc.dnvip.ui_lsn_3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * 环形渲染
 * 水波纹效果
 */
public class RadialGradientView extends View {

    private ObjectAnimator mObjectAnimator;
    private RadialGradient mRadialGradient;
    private Paint mPaint;
    private int mWidth, mHeight;

    public RadialGradientView(Context context) {
        this(context, null);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @SuppressLint("ObjectAnimatorBinding")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //开始绘制水波纹效果
//        canvas.save();3256.5  2078.10

        mObjectAnimator = ObjectAnimator.ofInt(this,"radius",10, mWidth);
        mObjectAnimator.setDuration(10000);
        mObjectAnimator.setInterpolator(new AccelerateInterpolator());
        mObjectAnimator.start();
        mObjectAnimator.addListener(new Animator.AnimatorListener(){

            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("tag",animation.getStartDelay()+"");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("tag",animation.getStartDelay()+"");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i("tag",animation.getStartDelay()+"");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i("tag",animation.getStartDelay()+"");
            }
        });
        mRadialGradient = new RadialGradient(mWidth/2, mWidth/2, mWidth, 0x00FFFFFF, 0xFF58FAAC, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);
//        canvas.setMatrix(mMatrix);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);

//        canvas.restore();
    }

}
