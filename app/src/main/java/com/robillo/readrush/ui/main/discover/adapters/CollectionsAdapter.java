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
import com.robillo.readrush.ui.main.discover.DiscoverFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;

/**
 * Created by robinkamboj on 20/10/17.
 */

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.CollectionsHolder> {

    private List<CollectionUnit> mList = new ArrayList<>();
    private Context mContext;
    private DiscoverFragment mDiscoverFragment;

    public CollectionsAdapter(List<CollectionUnit> mList, Context mContext, DiscoverFragment mDiscoverFragment) {
        this.mList = mList;
        this.mContext = mContext;
        this.mDiscoverFragment = mDiscoverFragment;
    }

    @Override
    public CollectionsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new CollectionsHolder(LayoutInflater.from(mContext).inflate(R.layout.row_collections, parent, false));
    }

    @Override
    public void onBindViewHolder(CollectionsHolder holder, int position) {
        final int pos = position;
        Glide.with(mContext).load(mList.get(pos).getCover_image()).centerCrop().into(holder.mCover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDiscoverFragment.fetchCollectionFromCid(mList.get(pos).getCollection_id(), mList.get(pos).getCollection_alias());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class CollectionsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover)
        ImageView mCover;

        CollectionsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
