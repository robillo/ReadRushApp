package com.robillo.readrush.ui.main.profile.profile_covers_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.ui.rushoverview.OverviewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 26/12/17.
 */

public class ProfileCoverAdapter extends RecyclerView.Adapter<ProfileCoverAdapter.ProfileCoverHolder> {

    private List<ProfileCover> mList = null;
    private Context mContext = null;

    public ProfileCoverAdapter(List<ProfileCover> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public ProfileCoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ProfileCoverHolder(LayoutInflater.from(mContext).inflate(R.layout.row_featured, parent, false));
    }

    @Override
    public void onBindViewHolder(ProfileCoverHolder holder, final int position) {
        //noinspection UnnecessaryLocalVariable
        final int pos = position;
        Glide.with(mContext).load(mList.get(pos).getCover()).centerCrop().crossFade().into(holder.cover);
        if(mList.get(position).getRush_id()!=null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(OverviewActivity.getStartIntent(mContext, mList.get(pos).getRush_id()));
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ProfileCoverHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cover)
        ImageView cover;

        ProfileCoverHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
