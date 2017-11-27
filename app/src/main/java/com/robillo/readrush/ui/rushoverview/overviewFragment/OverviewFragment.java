package com.robillo.readrush.ui.rushoverview.overviewFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;
import com.robillo.readrush.ui.main.discover.PagerFragment.PagerFragment;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends BaseFragment implements OverviewFragmentMvpView {

    @Inject
    OverviewFragmentMvpPresenter<OverviewFragmentMvpView> mPresenter;

    static String mRushId;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(Bundle bundle) {
        OverviewFragment fragment = new OverviewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_overview, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(OverviewFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(OverviewFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        //noinspection ConstantConditions
        mRushId = getArguments().getString("rush_id");
        Toast.makeText(getActivity(), " " + mRushId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setReviewsFragment() {
        //noinspection ConstantConditions
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.add(R.id.container, ReviewsFragment.newInstance(null)).commit();
    }

    @OnClick(R.id.reviews)
    public void seeReviews() {
        setReviewsFragment();
    }
}
