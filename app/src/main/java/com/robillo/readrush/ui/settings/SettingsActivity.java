package com.robillo.readrush.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.preference.PreferenceActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity implements SettingsMvpView {

    @BindView(R.id.notification_toggle)
    ToggleButton mNotificationsToggle;

    @BindView(R.id.membership_type)
    TextView mMembershipType;

    @BindView(R.id.update_preferences)
    TextView mUpdatePreferences;

    @BindView(R.id.about)
    TextView mAbout;

    @BindView(R.id.facebook)
    TextView mFacebook;

    @BindView(R.id.twitter)
    TextView mTwitter;

    @BindView(R.id.logout)
    TextView mLogout;

    @BindView(R.id.email_help)
    TextView mEmailHelp;

    @Inject
    SettingsMvpPresenter<SettingsMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getActivityComponent().inject(SettingsActivity.this);

        ButterKnife.bind(this);

        mPresenter.onAttach(this);
    }

    @Override
    protected void setUp() {

    }
}
