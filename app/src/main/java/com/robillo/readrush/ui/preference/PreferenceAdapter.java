package com.robillo.readrush.ui.preference;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    public void onBindViewHolder(PreferenceHolder holder, int position) {
        holder.textView.setText(mList.get(position));
        animateView(holder.linearLayout);
    }

    private void animateView(LinearLayout view) {

        Animator[] animators = new Animator[]{
                DefaultAnimations.shrinkAnimator(view, 800L),
                DefaultAnimations.fadeInAnimator(view, 800L)
        };

        InlineSort inlineSort = new InlineSort(100, false, CorneredSort.Corner.TOP_LEFT);

        new Spruce
                .SpruceBuilder(view)
                .sortWith(inlineSort)
                .animateWith(animators)
                .start();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class PreferenceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card)
        public CardView mCard;

        @BindView(R.id.text)
        public TextView textView;

        @BindView(R.id.linear)
        public LinearLayout linearLayout;

        public PreferenceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
