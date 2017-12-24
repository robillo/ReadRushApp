package com.robillo.readrush.ui.done_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;

import butterknife.ButterKnife;

public class DoneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        setUnBinder(ButterKnife.bind(this));

        setUp();
    }

    @Override
    protected void setUp() {

    }
}
