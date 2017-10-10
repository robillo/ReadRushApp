package com.robillo.readrush.ui.onboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.splash.SplashActivity;

public class OnboardActivity extends BaseActivity implements OnboardMvpView{


    public static Intent getStartIntent(Context context) {
        return new Intent(context, OnboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        setUp();
    }

    @Override
    protected void setUp() {

    }
}
