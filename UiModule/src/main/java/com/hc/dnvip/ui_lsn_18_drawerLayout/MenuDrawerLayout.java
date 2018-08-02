package com.hc.dnvip.ui_lsn_18_drawerLayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huchao on 2018/8/2.
 * 自定义的侧滑菜单
 */
public class MenuDrawerLayout extends DrawerLayout implements DrawerLayout.DrawerListener{


    public MenuDrawerLayout(@NonNull Context context) {
        super(context);
    }

    public MenuDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //布局加载完成后初始化新布局
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    //主体内光荣
    private View mContentView;
    //菜单内容
    private MenuContentView mMenuContentView;
    private MenuPutLayout mMenuPutLayout;

    private float mYPoint;
    private float mSlideOffset;
    /**
     * 初始化View
     */
    private void initView() {
        addDrawerListener(this);
        mContentView = getChildAt(0);
        mMenuContentView = (MenuContentView) getChildAt(1);
        //菜单内容变成透明色
        mMenuContentView.setBackgroundColor(Color.TRANSPARENT);
        //移除当前VIew
        removeView(mMenuContentView);
        //初始化putLayout
        mMenuPutLayout = new MenuPutLayout(mMenuContentView);
        mMenuPutLayout.setMenuBgColor(((ColorDrawable)getBackground()).getColor());
        addView(mMenuPutLayout);
        //设置当前控件背景透明
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mYPoint = ev.getY();
        if(mSlideOffset<1){
            return super.dispatchTouchEvent(ev);
        }else {
            mMenuPutLayout.setYPoint(mYPoint,mSlideOffset);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
//        Log.i("tag","菜单回调");
        mSlideOffset = slideOffset;
        mMenuPutLayout.setYPoint(mYPoint,slideOffset);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
