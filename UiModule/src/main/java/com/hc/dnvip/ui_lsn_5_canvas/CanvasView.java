package com.hc.dnvip.ui_lsn_5_canvas;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CanvasView extends View{

    private Paint mPaint;

    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xffff0000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        int i = canvas.saveLayer(0, 0, 0, 0, mPaint);

        for (int i = 0; i < 4; i++) {
            canvas.save();
            canvas.translate(i*160,0);
            canvas.drawRect(0,0,150,150,mPaint);
            canvas.restore();


//            int t = canvas.saveLayer(0,0,0,0,mPaint);
//            canvas.restoreToCount(1);

            Log.i("tag",canvas.getSaveCount()+"");
        }
    }
}
