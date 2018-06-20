package com.hc.dnvip.ui_lsn_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

public class LinearGradientView extends AppCompatTextView {


    private LinearGradient mLinearGradient;
    private TextPaint mTextPaint;

    private Matrix mMatrix;

    private float mTranslate;
    private float DELTAX = 20;

    public LinearGradientView(Context context) {
        this(context,null);
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinearGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextPaint = this.getPaint();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        String text = getText().toString();
        float textWith = mTextPaint.measureText(text);
        // 3个文字的宽度
        int gradientSize = (int) (textWith / text.length() * 3);
        //LinearGradient线性渲染，   X,Y,X1,Y1四个参数只定位效果，不定位位置
        mLinearGradient = new LinearGradient(-gradientSize,0,gradientSize,0,new int[]{
                0x22ffffff, 0xffffffff, 0x22ffffff},null, Shader.TileMode.CLAMP);
        mTextPaint.setShader(mLinearGradient);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTranslate += DELTAX;
        float textWidth = getPaint().measureText(getText().toString());
        //到底部进行返回
        if(mTranslate > textWidth + 1 || mTranslate < 1){
            DELTAX = -DELTAX;
//            mTranslate = -mTranslate;
        }

//        Log.i("tag",mTranslate+","+DELTAX);

        mMatrix = new Matrix();
        mMatrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        postInvalidateDelayed(50);

    }
}
