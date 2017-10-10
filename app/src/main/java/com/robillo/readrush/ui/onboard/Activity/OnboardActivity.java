package com.robillo.readrush.ui.onboard.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.splash.SplashActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class OnboardActivity extends BaseActivity implements OnboardMvpView{

    @Inject
    OnboardMvpPresenter<OnboardMvpView> mPresenter;

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

}
