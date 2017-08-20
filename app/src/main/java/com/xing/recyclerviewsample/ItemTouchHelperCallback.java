package com.xing.recyclerviewsample;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;

/**
 * Created by Administrator on 2017/8/20.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private RecyclerAdapter mAdapter;

    public ItemTouchHelperCallback(RecyclerAdapter adapter) {
        mAdapter = adapter;
    }

    /**
     * 通过返回值来设置是否处理某次拖拽或者滑动事件
     * dragFlags:拖拽标示
     * swipeFlags:滑动标示
     * 若都设置为0，暂时不考虑滑动相关操作
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                    | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
        return 0;
    }

    /**
     * 当长按并进入拖拽状态时，拖拽过程中将不断回调该方法
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 拖动的 item 的下标
        int fromPosition = viewHolder.getAdapterPosition();
        // 目标 item 的下标，拖拽过程中不断的和拖动的 item 做位置交换
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mAdapter.getDataList(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mAdapter.getDataList(), i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    /**
     * 滑动删除的回调
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int adapterPosition = viewHolder.getAdapterPosition();
        mAdapter.notifyItemRemoved(adapterPosition);
        mAdapter.getDataList().remove(adapterPosition);

    }

    /**
     * 当长按 item 刚开始拖拽的时候调用,设置拖拽的背景
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            // 给拖拽的 item 设置一个背景
            ((RecyclerAdapter.ItemViewHolder) viewHolder).itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当完成拖拽，手指松开的时候调用
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        // 给拖拽的 item 还原开始的背景
        ((RecyclerAdapter.ItemViewHolder) viewHolder).itemView.setBackgroundColor(Color.WHITE);
    }
}
