package com.robillo.readrush.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.login.fragment.LoginFragment;
import com.robillo.readrush.ui.onboard.OnboardActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.prev)
    TextView mTextPrevious;

    @BindView(R.id.next)
    FrameLayout mLinearNext;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getActivityComponent().inject(LoginActivity.this);

        setUnBinder(ButterKnife.bind(this));

//        mPresenter.onAttach(LoginActivity.this);

        setUp();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void setLoginFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.next, LoginFragment.newInstance()).commit();
    }

    @Override
    protected void setUp() {
        setLoginFragment();
    }

    @Override
    protected void onDestroy() {
//        mPresenter.onDetach();
        super.onDestroy();
    }

}
