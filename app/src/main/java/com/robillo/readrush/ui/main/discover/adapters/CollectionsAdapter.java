package com.robillo.readrush.ui.main.discover.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.data.network.retrofit.model.CollectionUnit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;

/**
 * Created by robinkamboj on 20/10/17.
 */

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.CollectionsHolder> {

    List<CollectionUnit> mList = new ArrayList<>();
    Context mContext;

    public CollectionsAdapter(List<CollectionUnit> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public CollectionsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new CollectionsHolder(LayoutInflater.from(mContext).inflate(R.layout.row_collections, parent, false));
    }

    @Override
    public void onBindViewHolder(CollectionsHolder holder, int position) {
//        holder.mHeader.setText(mList.get(position).getId());
        Glide.with(mContext).load(mList.get(position).getCover_image()).centerCrop().into(holder.mCover);

//                .bitmapTransform(new BrightnessFilterTransformation(mContext, -0.5f))
//                .centerCrop()
//                .into(holder.mCover);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class CollectionsHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.text)
//        public TextView mHeader;

        @BindView(R.id.cover)
        public ImageView mCover;

        public CollectionsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
