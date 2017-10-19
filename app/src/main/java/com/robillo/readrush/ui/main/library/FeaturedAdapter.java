package com.robillo.readrush.ui.main.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.robillo.readrush.R;
import com.robillo.readrush.data.others.Feature;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 20/10/17.
 */

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedHolder> {

    List<Feature> mList = new ArrayList<>();
    Context mContext;

    public FeaturedAdapter(List<Feature> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public FeaturedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FeaturedHolder holder, int position) {

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
