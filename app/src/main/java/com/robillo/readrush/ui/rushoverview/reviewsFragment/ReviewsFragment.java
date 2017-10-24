package com.robillo.readrush.ui.rushoverview.reviewsFragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;
import com.robillo.readrush.data.others.Review;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.rushoverview.overviewFragment.OverviewFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends BaseFragment implements ReviewsMvpView {

    ReviewsAdapter mAdapter;
    List<Review> mReviewList = new ArrayList<>();

    @BindView(R.id.reviews)
    RecyclerView mReviewRv;

    @Inject
    ReviewsMvpPresenter<ReviewsMvpView> mPresenter;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment newInstance(Bundle bundle) {
        //        fragment.setArguments(bundle);
        return new ReviewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reviews, container, false);

        ActivityComponent component = getActivityComponent();

        if(getActivityComponent()!=null){

            component.inject(ReviewsFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(ReviewsFragment.this);
        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        mReviewList.add(new Review());
        mReviewList.add(new Review());
        mReviewList.add(new Review());
        mAdapter = new ReviewsAdapter(getActivity(), mReviewList);
        mReviewRv.setAdapter(mAdapter);
        mReviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
