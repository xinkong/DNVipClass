package com.hc.dnvip.ui_lsn_18_drawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.hc.dnvip.R;

/**
 * 1.需要背景VIEW	绘制背景
 * 2.需要线性布局去进行布局拜访  专门负责摆放
 * 3.需要一个relatiLayout进行层叠  专门负责组合+转移数据
 * 4.需要一个DrawerLayout进行事件分发 专门负责进行时间的转发
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
//帝霸 最强反套路系统   重生之都市修仙 一念永恒  我修的可能是假仙 神道丹尊
        //45分钟
    }
}
