package com.robillo.readrush.ui.splash;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    Animation topDown, bottomUp;

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @BindView(R.id.ll_one)
    View mViewOne;

    @BindView(R.id.ll_two)
    View mViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mPresenter.startCountDown(4);
        topDown = AnimationUtils.loadAnimation(this, R.anim.top_down);
        bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        mViewOne.startAnimation(topDown);
        mViewTwo.startAnimation(bottomUp);
    }

    @Override
    public void openLoginActivity() {
        Toast.makeText(getApplicationContext(), "Opening Login Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openMainActivity() {

    }

    @Override
    public void startSyncService() {

    }
}
