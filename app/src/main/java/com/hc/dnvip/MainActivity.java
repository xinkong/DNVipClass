package com.hc.dnvip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hc.dnvip.ui_lsn_1.LSN1Activity;

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
        mRvClassContent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        mRvClassContent.setAdapter(new MyAdapter());
    }

    private void generateData() {
        mClassDis.add("高级UI---LSN-1-UI绘制流程详解(整体启动流程)");
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolde>{

        @NonNull
        @Override
        public MyViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolde(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_content_info,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolde holder, int position) {
            holder.name.setText(mClassDis.get(position));
            holder.itemView.setOnClickListener(view->{
                if(position == 0){
                    startActivity(new Intent(MainActivity.this, LSN1Activity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mClassDis.size();
        }
    }

    class MyViewHolde extends RecyclerView.ViewHolder{

        TextView name ;

        public MyViewHolde(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_class_dis);
        }
    }
}
