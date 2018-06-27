package com.hc.dnvip.ui_lsn_6_canvas_drawable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GallaryHorizontalScrollView extends HorizontalScrollView {

    private LinearLayout mContainer;
    private int mCenterX;
    private int mIcoeWidth;

    public GallaryHorizontalScrollView(Context context) {
        super(context);
        init();
    }
    public GallaryHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GallaryHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //设置padding使第一张图片到中间位置并初始化数据
        View v = mContainer.getChildAt(0);
        mIcoeWidth = v.getWidth();
        //得到hzv的中间x坐标
        mCenterX = getWidth()/2;
        //处理下，中心坐标改为中心图片的左边界
        mCenterX = mCenterX - mIcoeWidth/2;
        //给LinearLayout和hzv之间设置边框距离
        mContainer.setPadding(mCenterX, 0, mCenterX, 0);
    }

    //初始化
    private void init() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //去掉水平滚动条
        setHorizontalScrollBarEnabled(false);
        mContainer = new LinearLayout(getContext());
        mContainer.setLayoutParams(layoutParams);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

//        Log.i("tag","l="+l+",t="+t+",oldl="+oldl+",lodt="+oldt);

        reveal();
    }

    private void reveal() {
        // 渐变效果
        //得到hzv滑出去的距离
        int scrollX = getScrollX();
        //找到两张渐变的图片的下标--左，右
        int index_left = scrollX/mIcoeWidth;
        int index_right = index_left + 1;
        //设置图片的level
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            if(i==index_left||i==index_right){
                //变化
                //比例：

                float ratio = 5000f/mIcoeWidth;
                ImageView iv_left = (ImageView) mContainer.getChildAt(index_left);
                //scrollX%icon_width:代表滑出去的距离
                //滑出去了icon_width/2  icon_width/2%icon_width
                iv_left.setImageLevel(
                        //代表的是，我滑动之后的距离在5000份当中的份额

                        (int)(5000-scrollX%mIcoeWidth*ratio)
                );
                //右边
                if(index_right<mContainer.getChildCount()){
                    ImageView iv_right = (ImageView) mContainer.getChildAt(index_right);
                    //scrollX%icon_width:代表滑出去的距离
                    //滑出去了icon_width/2  icon_width/2%icon_width
                    iv_right.setImageLevel(
                            (int)(10000-scrollX%mIcoeWidth*ratio)
                    );
                }
            }else{
                //灰色
                ImageView iv = (ImageView) mContainer.getChildAt(i);
                iv.setImageLevel(0);
            }
        }



    }

    //添加图片的方法
    public void addImageViews(Drawable[] revealDrawables){
        for (int i = 0; i < revealDrawables.length; i++) {
            ImageView img = new ImageView(getContext());
            img.setImageDrawable(revealDrawables[i]);
            mContainer.addView(img);
            if(i==0){
                img.setImageLevel(5000);
            }
        }
        addView(mContainer);
    }

//    @Override
//    public void fling(int velocityX) {
//        super.fling(velocityX/1000);
//    }
}
