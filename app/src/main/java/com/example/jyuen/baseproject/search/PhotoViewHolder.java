package com.example.jyuen.baseproject.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jyuen.baseproject.R;
import com.example.jyuen.baseproject.model.flickr.Photo;
import com.example.jyuen.baseproject.widget.recyclerview.BaseRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jyuen on 1/18/16.
 */
public class PhotoViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
    @Bind(R.id.photo)
    ImageView mAvatar;

    public PhotoViewHolder (View itemView, SearchResultAdapter adapter) {
        super(itemView, adapter);
        ButterKnife.bind(this, itemView);
    }

    public void bind (Photo user) {
        Glide.with(itemView.getContext())
             .load(user.getUrl())
             .placeholder(R.mipmap.ic_launcher)
             .into(mAvatar);
    }
}
