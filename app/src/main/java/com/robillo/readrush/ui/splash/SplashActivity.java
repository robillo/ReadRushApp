package com.robillo.readrush.ui.splash;

import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUp();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openMainActivity() {

    }

    @Override
    public void startSyncService() {

    }
}
