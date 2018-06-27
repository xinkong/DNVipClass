package com.hc.dnvip.ui_lsn_6_canvas_drawable;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.hc.dnvip.R;

public class CanvasDrawableActivity extends AppCompatActivity {

    ImageView mImageView;
    GallaryHorizontalScrollView mGallaryHorizontalScrollView;


    private int[] mImgIds = new int[] { //7个
            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline,

            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline
    };
    private int[] mImgIds_active = new int[] {
            R.mipmap.avft_active, R.mipmap.box_stack_active, R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active, R.mipmap.bullseye_active, R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active,
            R.mipmap.avft_active, R.mipmap.box_stack_active, R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active, R.mipmap.bullseye_active, R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active
    };

    public Drawable[] revealDrawables = new Drawable[mImgIds.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_drawable);

        mImageView = findViewById(R.id.iv);
        RevealDrawable drawable = new RevealDrawable(getResources().getDrawable(R.mipmap.avft),getResources().getDrawable(R.mipmap.avft_active));
        mImageView.setImageDrawable(drawable);

        mGallaryHorizontalScrollView = findViewById(R.id.GallaryHorizontalScrollView);
        initView();

    }

    private void initView()
    {
        for (int i = 0; i < mImgIds.length; i++)
        {
            RevealDrawable rd = new RevealDrawable(
                    getResources().getDrawable(mImgIds[i]),
                    getResources().getDrawable(mImgIds_active[i]));
            revealDrawables[i] = rd;
        }
        mGallaryHorizontalScrollView.addImageViews(revealDrawables);

    }
}
