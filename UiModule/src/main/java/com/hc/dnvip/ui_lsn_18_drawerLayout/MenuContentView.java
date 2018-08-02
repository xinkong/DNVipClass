package com.hc.dnvip.ui_lsn_18_drawerLayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by huchao on 2018/8/2.
 * 内容区域
 */
public class MenuContentView extends LinearLayout{
    public MenuContentView(Context context) {
        this(context,null);
    }

    public MenuContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MenuContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    //初始化view
    private void initView() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    /**
     * 设置随手指移动的效果
     * @param yPoint
     */
    public void setYPoint(float yPoint,float percent){
        //设置内容x轴便宜
        setTranslationX(percent);
    }

}
