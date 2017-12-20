package com.robillo.readrush.ui.settings;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.preference.PreferenceActivity;
import com.robillo.readrush.ui.splash.SplashActivity;
import com.robillo.readrush.ui.webview.WebViewActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity implements SettingsMvpView {

    @BindView(R.id.notification_toggle)
    Switch mNotificationsToggle;

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

    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;

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
        mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
    }

    @OnClick(R.id.about)
    public void setmAbout() {
        startActivity(WebViewActivity.getStartIntent(this, "About", "http://readrush.in/index.php/home/about_us"));
    }

    @OnClick(R.id.twitter)
    public void setmTwitter() {
        startActivity(WebViewActivity.getStartIntent(this, "Twitter", "https://twitter.com/readrush"));
    }

    @OnClick(R.id.facebook)
    public void setmFacebook() {
        startActivity(WebViewActivity.getStartIntent(this, "Facebook", "https://www.facebook.com/readrush"));
    }

    @OnClick(R.id.membership_type)
    public void setmMembershipType() {
        Toast.makeText(this, "Normal Membership", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.email_help)
    public void setmEmailHelp() {
        String subject = "Help";
        String bodyText = "ReadRush, ";

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","upendra@readrush.in", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, bodyText);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (ActivityNotFoundException e) {
            Log.e("EXCEPTION", "ACTIVITY NOT FOUND");
        }
    }

    @OnClick(R.id.logout)
    public void setmLogout() {
        mPrefsHelper.setUserIsOnBoarded(false);
        mPrefsHelper.setUserIsLoggedIn(false);
        startActivity(SplashActivity.getStartIntent(this));
    }
}
