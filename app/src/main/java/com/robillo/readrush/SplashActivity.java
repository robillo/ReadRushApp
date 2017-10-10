package com.robillo.readrush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robillo.readrush.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
    }

    @Override
    protected void setUp() {

    }
}
