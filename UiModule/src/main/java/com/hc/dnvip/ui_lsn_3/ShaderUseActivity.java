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

    TextView mBitmapShader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader_use);
        mBitmapShader = findViewById(R.id.bitmapshader);
        mBitmapShader.setOnClickListener(v -> startActivity(new Intent(this,BitmapShaderActivity.class)));
    }
}
