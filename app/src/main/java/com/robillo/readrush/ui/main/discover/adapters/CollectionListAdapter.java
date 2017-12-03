package com.robillo.readrush.ui.main.discover.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.data.network.retrofit.model.CollectionListItem;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 03/12/17.
 */

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.CollectionListHolder> {

    @SuppressWarnings("FieldCanBeLocal")
    private List<CollectionListItem> mList;
    private Context mContext;
    private DiscoverFragment mDiscoverFragment;

    public CollectionListAdapter(List<CollectionListItem> mList, Context mContext, DiscoverFragment mDiscoverFragment) {
        this.mList = mList;
        this.mContext = mContext;
        this.mDiscoverFragment = mDiscoverFragment;
    }

    @Override
    public CollectionListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new CollectionListHolder(LayoutInflater.from(mContext).inflate(R.layout.row_featured, parent, false));
    }

    @Override
    public void onBindViewHolder(CollectionListHolder holder, final int position) {
        //noinspection UnnecessaryLocalVariable
        final int pos = position;
        Glide.with(mContext).load(mList.get(pos).getCover_image()).centerCrop().into(holder.mCover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDiscoverFragment.fetchCollectionFromCid(mList.get(pos).getCollection_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CollectionListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover)
        ImageView mCover;

        CollectionListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
