package com.xing.recyclerviewsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<Bean> dataList;

    public RecyclerAdapter(Context context, List<Bean> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }

    public void setDataList(List<Bean> data) {
        this.dataList = data;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_list_recycler, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bindData(dataList, position);
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public List<Bean> getDataList() {
        return dataList;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_icon);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void bindData(List<Bean> data, int position) {
            Bean bean = data.get(position);
            Glide.with(mContext).load(bean.getUrl()).into(imageView);
            textView.setText(bean.getTitle());
        }
    }
}
