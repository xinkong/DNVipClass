package com.hc.dnvip.ui_lsn_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hc.dnvip.R;

/**
 * Shader渲染使用
 */
public class ShaderUseActivity extends AppCompatActivity {

    TextView mBitmapShader,mLinearGradient,mSweepGradient,mRadialGradient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader_use);
        mBitmapShader = findViewById(R.id.bitmapshader);
        mLinearGradient = findViewById(R.id.LinearGradient);
        mSweepGradient = findViewById(R.id.SweepGradient);
        mRadialGradient = findViewById(R.id.RadialGradient);
        mBitmapShader.setOnClickListener(v -> startActivity(new Intent(this,BitmapShaderActivity.class)));
        mLinearGradient.setOnClickListener(v -> startActivity(new Intent(this,LinearGradientActivity.class)));
        mSweepGradient.setOnClickListener(v -> startActivity(new Intent(this,SweepGradientActivity.class)));
        mRadialGradient.setOnClickListener(v -> startActivity(new Intent(this,RadialGradientActivity.class)));
    }
}
