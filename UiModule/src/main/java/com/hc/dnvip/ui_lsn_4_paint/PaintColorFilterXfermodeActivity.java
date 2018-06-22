package com.hc.dnvip.ui_lsn_4_paint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hc.dnvip.R;

/**
 * Lsn4_Paint-滤镜,XFERMODE
 */
public class PaintColorFilterXfermodeActivity extends AppCompatActivity {

    TextView mTVColorFilter,mTvXfermode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_color_filter_xformode);
        mTVColorFilter = findViewById(R.id.colorFilter);
        mTvXfermode = findViewById(R.id.Xfermode);

        mTVColorFilter.setOnClickListener(v ->startActivity(new Intent(this,ColorFilterActivity.class)));
        mTvXfermode.setOnClickListener(v ->startActivity( new Intent(this,XfermodeActivity.class)));

    }
}
