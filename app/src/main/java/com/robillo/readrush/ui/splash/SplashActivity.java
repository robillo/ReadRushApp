package com.robillo.readrush.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.login.LoginActivity;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.onboard.OnboardActivity;

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

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(SplashActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        mPresenter.startCountDown(1500);
        topDown = AnimationUtils.loadAnimation(this, R.anim.top_down);
        bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        mViewOne.startAnimation(topDown);
        mViewTwo.startAnimation(bottomUp);
    }

    @Override
    public void openOnBoardActivity() {
        startActivity(OnboardActivity.getStartIntent(this));
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(this));
    }

    @Override
    public void startSyncService() {

    }

    @Override
    public void startNextActivity() {
        AppPreferencesHelper helper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
        if(!helper.getUserIsOnBoarded()){
            openOnBoardActivity();
        }
        else if(!helper.getUserIsLoggedIn()){
            openLoginActivity();
        }
        else {
            openMainActivity();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
