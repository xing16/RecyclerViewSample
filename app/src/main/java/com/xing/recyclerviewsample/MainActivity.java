package com.xing.recyclerviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<Bean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setListener();
    }

    private void setListener() {
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int adapterPosition = viewHolder.getAdapterPosition();
                Toast.makeText(MainActivity.this, data.get(adapterPosition).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {

            }
        });
    }


    private void initData() {
        data = new ArrayList<>();
        data.add(new Bean("https://ws1.sinaimg.cn/large/610dc034ly1fik2q1k3noj20u00u07wh.jpg", "Android"));
        data.add(new Bean("https://ws1.sinaimg.cn/large/610dc034ly1fiiiyfcjdoj20u00u0ju0.jpg", "iOS"));
        data.add(new Bean("https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg", "Java"));
        data.add(new Bean("https://ws1.sinaimg.cn/large/610dc034gy1fi502l3eqjj20u00hz41j.jpg", "python"));
        for (int i = 0; i < 20; i++) {
            data.add(new Bean("https://ws1.sinaimg.cn/large/610dc034ly1fil82i7zsmj20u011hwja.jpg", "MackBook Pro - " + i));
        }

        mAdapter = new RecyclerAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(this));
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
