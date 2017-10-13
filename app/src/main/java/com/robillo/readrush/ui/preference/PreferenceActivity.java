package com.robillo.readrush.ui.preference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PreferenceActivity extends BaseActivity implements PreferenceMvpView {

    @Inject
    PreferenceMvpPresenter<PreferenceMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, PreferenceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        getActivityComponent().inject(PreferenceActivity.this);

        ButterKnife.bind(this);

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {

    }
}
