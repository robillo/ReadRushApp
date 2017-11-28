package com.robillo.readrush.ui.main.discover.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.data.network.retrofit.model.Featured;
import com.robillo.readrush.data.network.retrofit.model.SearchResultItem;
import com.robillo.readrush.ui.rushoverview.OverviewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 20/10/17.
 */

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedHolder> {

    private List<Featured> mList = new ArrayList<>();
    private List<SearchResultItem> mSearchList = new ArrayList<>();
    private Context mContext;

    public FeaturedAdapter(List<Featured> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public FeaturedAdapter(Context mContext, List<SearchResultItem> mList) {
        this.mSearchList = mList;
        this.mContext = mContext;
    }

    @Override
    public FeaturedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new FeaturedHolder(LayoutInflater.from(mContext).inflate(R.layout.row_featured, parent, false));
    }

    @Override
    public void onBindViewHolder(FeaturedHolder holder, final int position) {
        //noinspection UnnecessaryLocalVariable
        final int pos = position;

        if(mSearchList.size()>0){
            Glide.with(mContext).load(mSearchList.get(0).getCover()).centerCrop().crossFade().into(holder.cover);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(OverviewActivity.getStartIntent(mContext, mSearchList.get(pos).getRush_id()));
                }
            });
        }
        else {
            Glide.with(mContext).load(mList.get(position).getCover_image()).centerCrop().crossFade().into(holder.cover);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(OverviewActivity.getStartIntent(mContext, mList.get(pos).getRush_id()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class FeaturedHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover)
        ImageView cover;

        public FeaturedHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
