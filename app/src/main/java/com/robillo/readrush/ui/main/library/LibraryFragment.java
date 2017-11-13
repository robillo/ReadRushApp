package com.robillo.readrush.ui.main.library;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.onboard.fragment.OnboardFragment;
import com.robillo.readrush.ui.rushoverview.OverviewActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends BaseFragment implements LibraryMvpView {

    public static int RUSH_COUNT = 0;

    @BindView(R.id.error_layout)
    LinearLayout mErrorLayout;

    @BindView(R.id.main_layout)
    LinearLayout mMainLayout;

    @BindView(R.id.error_drawable)
    ImageView mErrorDrawable;

    @BindView(R.id.error_header)
    TextView mErrorHeader;

    @BindView(R.id.error_description)
    TextView mErrorDescription;

    @BindView(R.id.rush1)
    ImageView mRushOne;

    @BindView(R.id.rush2)
    ImageView mRushTwo;

    @BindView(R.id.rush3)
    ImageView mRushThree;

    @BindView(R.id.rush4)
    ImageView mRushFour;

    @BindView(R.id.rush5)
    ImageView mRushFive;

    @BindView(R.id.rush6)
    ImageView mRushSix;

    @BindView(R.id.refresh_buttom)
    Button mRefreshButton;

    @Inject
    LibraryMvpPresenter<LibraryMvpView> mPresenter;

    public LibraryFragment() {
        // Required empty public constructor
    }

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_library, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(LibraryFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(LibraryFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        checkForExistingRushes();
    }

    @Override
    public void loadRushes() {
        if(mMainLayout.getVisibility()==View.GONE){
            mMainLayout.setVisibility(View.VISIBLE);
        }
        Glide.with(this).load(R.drawable.cover1).crossFade(400).centerCrop().into(mRushOne);
        Glide.with(this).load(R.drawable.cover2).crossFade(400).centerCrop().into(mRushTwo);
        Glide.with(this).load(R.drawable.cover3).crossFade(400).centerCrop().into(mRushThree);
        Glide.with(this).load(R.drawable.cover4).crossFade(400).centerCrop().into(mRushFour);
        Glide.with(this).load(R.drawable.cover6).crossFade(400).centerCrop().into(mRushFive);
    }

    @Override
    public void checkForExistingRushes() {
        if(RUSH_COUNT == 0){
            if(mMainLayout.getVisibility()==View.VISIBLE){
                mMainLayout.setVisibility(View.GONE);
            }
            showNoRushes();
        }
        else {
            if(mErrorLayout.getVisibility()==View.VISIBLE){
                mErrorLayout.setVisibility(View.GONE);
            }
            loadRushes();
        }
    }

    @Override
    public void showNoRushes() {
        if(mErrorLayout.getVisibility()==View.GONE){
            mErrorLayout.setVisibility(View.VISIBLE);
        }
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
        mErrorDrawable.setAnimation(animation);
        mErrorDescription.setAnimation(animation);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
        mErrorHeader.setAnimation(animation);
    }

    @OnClick(R.id.rush3)
    public void clickmRushThree() {
        startActivity(OverviewActivity.getStartIntent(getActivity()));
    }

    @OnClick(R.id.refresh_buttom)
    public void setmRefreshButton(){
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.refreshLibraryRushes();
        }
    }
}
