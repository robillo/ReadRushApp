package com.robillo.readrush.ui.preference;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.CorneredSort;
import com.willowtreeapps.spruce.sort.InlineSort;
import com.willowtreeapps.spruce.sort.LinearSort;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 13/10/17.
 */

public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceAdapter.PreferenceHolder> {

    private List<String> mList = new ArrayList<>();
    public List<String> mSelectedItems = new ArrayList<>();
    private Context mContext;

    public PreferenceAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public PreferenceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new PreferenceHolder(LayoutInflater.from(mContext).inflate(R.layout.row_preferences, parent, false));
    }

    @Override
    public void onBindViewHolder(final PreferenceHolder holder, final int position) {
        @SuppressWarnings("UnnecessaryLocalVariable") final int pos = position;
        holder.textView.setText(mList.get(pos));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.textView.getCurrentTextColor() == mContext.getResources().getColor(R.color.readBlack)) {
                    if(mSelectedItems.size()>=2){
                        Toast.makeText(mContext, "You Can Only Add Two Preferences", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        holder.textView.setTextColor(mContext.getResources().getColor(R.color.rushRed));
                        mSelectedItems.add(mList.get(pos));
                    }
                }
                else {
                    holder.textView.setTextColor(mContext.getResources().getColor(R.color.readBlack));
                    mSelectedItems.remove(mList.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class PreferenceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        public TextView textView;

        public PreferenceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
