package com.hc.dnvip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hc.dnvip.ui_lsn_1.LSN1Activity;
import com.hc.dnvip.ui_lsn_10_event.EventActivity;
import com.hc.dnvip.ui_lsn_12_loadAnim.LoadAnimActivity;
import com.hc.dnvip.ui_lsn_18_drawerLayout.DrawerLayoutActivity;
import com.hc.dnvip.ui_lsn_2.LSN2Activity;
import com.hc.dnvip.ui_lsn_3.ShaderUseActivity;
import com.hc.dnvip.ui_lsn_4_paint.PaintColorFilterXfermodeActivity;
import com.hc.dnvip.ui_lsn_5_canvas.CanvasActivity;
import com.hc.dnvip.ui_lsn_6_canvas_drawable.CanvasDrawableActivity;
import com.hc.dnvip.ui_lsn_7_Bezier.BezierQQActivity;
import com.hc.dnvip.ui_lsn_8_PathMeasure.PathMeasureActivity;
import com.hc.dnvip.ui_study_myself.heart.QiXiHeartActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> mClassDis = new ArrayList<>();
    RecyclerView mRvClassContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateData();

        mRvClassContent = findViewById(R.id.rv_class_content);
        mRvClassContent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvClassContent.setAdapter(new MyAdapter());

        NestedScrollView v ;
    }


    private void generateData() {
        mClassDis.add("高级UI---UI绘制流程详解(整体启动流程)");
        mClassDis.add("高级UI---UI绘制流程_UI具体绘制（测量流程）");
        mClassDis.add("高级UI---Shader,高级渲染");
        mClassDis.add("高级UI---滤镜,XFERMODE");
        mClassDis.add("高级UI---Lsn5_Canvas");
        mClassDis.add("高级UI---Canvas-Drawable实际案例操作");
        mClassDis.add("高级UI---贝塞尔曲线 QQ气泡效果");
        mClassDis.add("高级UI---PathMeasure");
        mClassDis.add("高级UI---事件分发");
        mClassDis.add("高级UI---属性动画,加载动画");
        mClassDis.add("高级UI---自定义侧滑菜单");
        mClassDis.add("自学---心行控件");
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolde> {

        @NonNull
        @Override
        public MyViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolde(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_content_info, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolde holder, int position) {
            holder.name.setText(mClassDis.get(position));
            holder.itemView.setOnClickListener(view -> {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, LSN1Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, LSN2Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ShaderUseActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, PaintColorFilterXfermodeActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, CanvasActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, CanvasDrawableActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, BezierQQActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, PathMeasureActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, EventActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, LoadAnimActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, QiXiHeartActivity.class));
                        break;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mClassDis.size();
        }
    }

    class MyViewHolde extends RecyclerView.ViewHolder {

        TextView name;

        public MyViewHolde(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_class_dis);
        }
    }
}
