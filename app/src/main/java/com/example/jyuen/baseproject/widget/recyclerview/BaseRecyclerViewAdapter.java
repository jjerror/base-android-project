package com.example.jyuen.baseproject.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jyuen on 1/19/16.
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends BaseRecyclerViewAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    public interface OnItemClickListener<T> {
        void onItemClick (View v, T item, int position);
    }

    private OnItemClickListener<T> mListener;

    public abstract T getItem (int position);

    public void setOnItemClickListener (OnItemClickListener<T> listener) {
        mListener = listener;
    }

    public void onItemClick (ViewHolder v) {
        if (mListener != null) {
            mListener.onItemClick(v.itemView, getItem(v.getLayoutPosition()), v.getLayoutPosition());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private BaseRecyclerViewAdapter mAdapter;

        public ViewHolder (View itemView) {
            this(itemView, null);
        }

        public ViewHolder (View itemView, BaseRecyclerViewAdapter adapter) {
            super(itemView);
            if (adapter != null) {
                mAdapter = adapter;
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick (View v) {
            mAdapter.onItemClick(this);

        }
    }
}
