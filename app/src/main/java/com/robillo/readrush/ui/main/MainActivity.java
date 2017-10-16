package com.robillo.readrush.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanks.htextview.base.HTextView;
import com.hanks.htextview.scale.ScaleTextView;
import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.onboard.OnboardActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import devlight.io.library.ntb.NavigationTabBar;

import static com.robillo.readrush.R.array.colors;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.header)
    TextView mHeader;

    @BindView(R.id.library)
    ImageView mLibrary;

    @BindView(R.id.discover)
    ImageView mDiscover;

    @BindView(R.id.profile)
    ImageView mProfile;

    @BindView(R.id.add)
    ImageView mAdd;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(MainActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);

        setUp();

    }

    @Override
    protected void setUp() {
        setmLibrary();
    }

    @Override
    public void setLibraryFragment() {

    }

    @Override
    public void setDiscoverFragment() {

    }

    @Override
    public void setProfileFragment() {

    }

    @Override
    public void setBottomNavigationTint(int position) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.header);
        switch (position){
            case 0:{
                mHeader.setText(getString(R.string.library));
                mHeader.startAnimation(animation);
                mLibrary.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne));
                mDiscover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextThree));
                mProfile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextThree));
                break;
            }
            case 1:{
                mHeader.startAnimation(animation);
                mHeader.setText(getString(R.string.discover));
                mLibrary.setColorFilter(ContextCompat.getColor(this, R.color.colorTextThree));
                mDiscover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne));
                mProfile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextThree));
                break;
            }
            case 2:{
                mHeader.startAnimation(animation);
                mHeader.setText(getString(R.string.profile));
                mLibrary.setColorFilter(ContextCompat.getColor(this, R.color.colorTextThree));
                mDiscover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextThree));
                mProfile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne));
                break;
            }
        }
    }

    @OnClick(R.id.library)
    public void setmLibrary() {
        setBottomNavigationTint(0);
        setLibraryFragment();
    }

    @OnClick(R.id.discover)
    public void setmDiscover() {
        setBottomNavigationTint(1);
        setDiscoverFragment();
    }

    @OnClick(R.id.profile)
    public void setmProfile() {
        setBottomNavigationTint(2);
        setProfileFragment();
    }
}
