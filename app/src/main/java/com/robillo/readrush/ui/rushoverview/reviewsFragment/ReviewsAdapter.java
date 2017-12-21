package com.robillo.readrush.ui.rushoverview.reviewsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.data.network.retrofit.model.Review;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        //noinspection UnnecessaryLocalVariable
        int pos = position;
        holder.mName.setText(mReviews.get(pos).getName());
        holder.mRating.setRating(Float.valueOf(mReviews.get(pos).getRating()));
        holder.mTime.setText(mReviews.get(pos).getDatetime());
        holder.mDescription.setText(mReviews.get(pos).getReview());
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    class ReviewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView mName;

        @BindView(R.id.time)
        TextView mTime;

        @BindView(R.id.rating)
        ScaleRatingBar mRating;

        @BindView(R.id.description)
        TextView mDescription;

        ReviewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
