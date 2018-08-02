package com.hc.dnvip.ui_lsn_18_drawerLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by huchao on 2018/8/2.
 * 内容组合区域(用于组合背景和餐单内容)
 * <p>
 * 改View不会再外部直接使用,所以要有初始params
 */
public class MenuPutLayout extends RelativeLayout {


    private MenuBgView mMenuBgView;
    private MenuContentView mMenuContentView;
    private Context mContext;


    public MenuPutLayout(MenuContentView menuContentView) {
        this(menuContentView.getContext());
        initView(menuContentView);
    }

    public MenuPutLayout(Context context) {
        this(context, null);
        mContext = context;
    }

    public MenuPutLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuPutLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(MenuContentView menuContentView) {
        mMenuContentView = menuContentView;
        setLayoutParams(menuContentView.getLayoutParams());

        //把背景引入
        mMenuBgView = new MenuBgView(mContext);
        //添加背景
        addView(mMenuBgView);
        //添加内容区域
        addView(mMenuContentView);
    }

    //设置背景View的背景颜色
    public void setMenuBgColor(int color){
        mMenuBgView.setColor(color);
    }


    //设置y和偏移量
    public void setYPoint(float yPoint,float percent){
        mMenuBgView.setYPoint(yPoint,percent);
        mMenuContentView.setYPoint(yPoint,percent);
    }

}
