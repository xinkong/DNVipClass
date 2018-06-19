package com.hc.dnvip.ui_lsn_2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hc.dnvip.R;

import java.util.ArrayList;
import java.util.List;

public class WaterfallFLowLayout extends ViewGroup {

    //判断是不是第一次进入
    private boolean mIsFirst = true;

    private LayoutInflater mInflater;

    //行高纪录
    List<Integer> lstHeights = new ArrayList<>();
    //每一行的视图
    List<List<View>> lstLineView = new ArrayList<>();

    public WaterfallFLowLayout(Context context) {
        this(context, null);
    }

    public WaterfallFLowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterfallFLowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mInflater = LayoutInflater.from(context);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mIsFirst) {
            mIsFirst = false;
            return;
        }
        //此处我门可以知道这里是我们的爸爸的SIZE
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        //当前空间宽高
        int measureWidth = 0;
        int measureHeight = 0;

        //当前行宽，行高，因为存在多行，下一行数据要放到下方，行高需要保存
        int iCurLineW = 0;
        int iCurLineH = 0;

        if (heightMode == MeasureSpec.AT_MOST) {
            measureHeight = calculationHeight(widthMeasureSpec, heightMeasureSpec, widthSize, measureWidth, measureHeight, iCurLineW, iCurLineH);
        } else if (heightMode == MeasureSpec.EXACTLY) {
            calculationHeight(widthMeasureSpec, heightMeasureSpec, widthSize, measureWidth, measureHeight, iCurLineW, iCurLineH);
            measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), measureHeight);
    }

    private int calculationHeight(int widthMeasureSpec, int heightMeasureSpec, int widthSize, int measureWidth, int measureHeight, int iCurLineW, int iCurLineH) {
        //当前VIEW宽高
        int iChildWidth = 0;
        int iChildHeight = 0;
        //获取子VIEW数量用于迭代
        int childCount = getChildCount();


        //单行信息容器
        List<View> viewList = new ArrayList<>();
        //计算高度
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            //2.获取getLayoutParams 即XML资源
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();


            //3.获得实际宽度和高度(MARGIN+WIDTH)
            iChildWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            iChildHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            //4.是否需要换行
            if (iCurLineW + iChildWidth > widthSize) {
                //4.1.纪录当前行信息
                //4.1.1.纪录当前行最大宽度，高度累加
                measureWidth = Math.max(measureWidth, iCurLineW);
                measureHeight += iCurLineH;
                //4.1.2.保存这一行数据，及行高
                lstHeights.add(iCurLineH);
                lstLineView.add(viewList);

                //4.2.纪录新的行信息
                //4.2.1.赋予新行新的宽高
                iCurLineW = iChildWidth;
                iCurLineH = iChildHeight;

                //4.2.2添加新行纪录
                viewList = new ArrayList<View>();
                viewList.add(childAt);

            } else {
                //5.1.不换行情况
                //5.1.1.记录某行内的消息行内宽度的叠加、高度比较
                iCurLineW += iChildWidth;
                iCurLineH = Math.max(iCurLineH, iChildHeight);

                //5.1.2.添加至当前行的viewList中
                viewList.add(childAt);
            }

            //6.如果正好是最后一行需要换行
            if (i == childCount - 1) {
                //6.1.记录当前行的最大宽度，高度累加
                measureWidth = Math.max(measureWidth, iCurLineW);
                measureHeight += iCurLineH;


                //6.2.将当前行的viewList添加至总的mViewsList，将行高添加至总的行高List
                lstLineView.add(viewList);
                lstHeights.add(iCurLineH);

            }

        }
        return measureHeight;
    }

    public void setLabels(List<String> labels) {
        this.removeAllViews();
        for (String lab : labels) {
            TextView tv = (TextView) mInflater.inflate(R.layout.item_lsn2_water, this, false);
            tv.setText(lab);
            addView(tv);
        }
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //开始布局
        //1.取得所有视图信息
        //与之当前组件上下左右四个编剧
        int left, top, right, bottom;
        //当前顶部高度和左部高度
        int curTop = 0;
        int curLeft = 0;
        //开始迭代
        int lineCount = lstLineView.size();
        for (int i = 0; i < lineCount; i++) {
            List<View> viewList = lstLineView.get(i);
            int lineViewSize = viewList.size();
            for (int j = 0; j < lineViewSize; j++) {
                View childView = viewList.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();


                left = curLeft + layoutParams.leftMargin;
                top = curTop + layoutParams.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
                //同理，通过调用自身的layout进行布局
                childView.layout(left, top, right, bottom);
                //左边部分累加
                curLeft += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            curLeft = 0;
            curTop += lstHeights.get(i);
        }
        lstLineView.clear();
        lstHeights.clear();
    }
}
