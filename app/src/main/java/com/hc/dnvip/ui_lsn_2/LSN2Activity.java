package com.hc.dnvip.ui_lsn_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hc.dnvip.R;

import java.util.ArrayList;
import java.util.List;

public class LSN2Activity extends AppCompatActivity {

    WaterfallFLowLayout mWaterfallFLowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsn2);
        mWaterfallFLowLayout = findViewById(R.id.waterfallFlow);
        List<String> lab = new ArrayList<>();
        lab.add("还好111");
        lab.add("还好111321223");
        lab.add("1");
        lab.add("还好1阿斯顿发送到11");
        lab.add("还好啊啊啊啊啊111");
        lab.add("还好1订单11");
        lab.add("还好1啊是打发斯蒂芬11");
        lab.add("还好啊111");
        lab.add("还好啊111");
        lab.add("还好大沙发都是111");
        lab.add("还好111");
        lab.add("还好111");
        lab.add("还asdfa好111");
        mWaterfallFLowLayout.setLabels(lab);
    }
}
