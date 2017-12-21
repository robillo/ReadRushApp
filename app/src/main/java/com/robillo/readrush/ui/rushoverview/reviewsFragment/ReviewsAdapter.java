package com.robillo.readrush.ui.rushoverview.reviewsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;
import com.robillo.readrush.data.network.retrofit.model.Review;

import java.util.List;

/**
 * Created by robinkamboj on 24/10/17.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsHolder> {

    private Context mContext;
    private List<Review> mReviews;

    ReviewsAdapter(Context mContext, List<Review> mReviews) {
        this.mContext = mContext;
        this.mReviews = mReviews;
    }

    @Override
    public ReviewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ReviewsHolder(LayoutInflater.from(mContext).inflate(R.layout.row_review, parent, false));
    }

    @Override
    public void onBindViewHolder(ReviewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    class ReviewsHolder extends RecyclerView.ViewHolder {

        public ReviewsHolder(View itemView) {
            super(itemView);
        }
    }
}
