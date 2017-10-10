package com.robillo.readrush.ui.onboard.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.splash.SplashActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardActivity extends BaseActivity implements OnboardMvpView{

    @Inject
    OnboardMvpPresenter<OnboardMvpView> mPresenter;

    @BindView(R.id.prev)
    Button previous;

    @BindView(R.id.next)
    Button next;

    @BindView(R.id.pager)
    ViewPager mPager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, OnboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        getActivityComponent().inject(OnboardActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(OnboardActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
