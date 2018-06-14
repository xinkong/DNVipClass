package com.hc.dnvip.ui_lsn_1;

import android.app.ActivityThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewManager;
import android.view.WindowManagerGlobal;
import android.view.WindowManagerImpl;

import com.android.internal.policy.PhoneWindow;
import com.hc.dnvip.R;

/**
 * 高级UI---LSN-1-UI绘制流程详解(整体启动流程)
 */
public class LSN1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsn1);
        /**
         * 源码参考
         * https://www.jianshu.com/p/0f6b4bc86c7b
         */
        ActivityThread activityThread;
        WindowManagerImpl windowManager;
        WindowManagerGlobal windowManagerGlobal;
        PhoneWindow phoneWindow;
    }
}
