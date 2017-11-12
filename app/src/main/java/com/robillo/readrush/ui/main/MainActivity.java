package com.robillo.readrush.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;
import com.robillo.readrush.ui.main.library.LibraryFragment;
import com.robillo.readrush.ui.main.profile.ProfileFragment;
import com.robillo.readrush.ui.search.SearchActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.search)
    ImageView mSearch;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    AppPreferencesHelper mPrefsHelper;

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
        mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
        mPrefsHelper.setUserIsLoggedIn(true);
        setmLibrary();
    }

    @Override
    public void setLibraryFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, LibraryFragment.newInstance(), getString(R.string.library)).commit();
    }

    @Override
    public void setDiscoverFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, DiscoverFragment.newInstance(), getString(R.string.discover)).commit();
    }

    @Override
    public void setProfileFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ProfileFragment.newInstance(), getString(R.string.profile)).commit();
    }

    @Override
    public void setBottomNavigationTint(int position) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.top_down);
        switch (position){
            case 0:{
                mHeader.setText(getString(R.string.library));
                mHeader.startAnimation(animation);
                mLibrary.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne));
                mDiscover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour));
                mProfile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour));
                break;
            }
            case 1:{
                mHeader.startAnimation(animation);
                mHeader.setText(getString(R.string.discover));
                mLibrary.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour));
                mDiscover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne));
                mProfile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour));
                break;
            }
            case 2:{
                mHeader.startAnimation(animation);
                mHeader.setText(getString(R.string.profile));
                mLibrary.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour));
                mDiscover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour));
                mProfile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne));
                break;
            }
        }
    }

    @Override
    public void refreshLibraryRushes() {
        LibraryFragment fragment = (LibraryFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.library));
        if(fragment!=null && fragment.isVisible()){
            LibraryFragment.RUSH_COUNT++;
            fragment.checkForExistingRushes();
        }
    }

    @Override
    public void setUpWindowAnimations() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.RIGHT);
            slide.setDuration(400);
            getWindow().setEnterTransition(slide);
        }
    }

    @Override
    public void hideAndUnclickableSearch() {
        if(mSearch.getVisibility()==View.VISIBLE){
            mSearch.setVisibility(View.INVISIBLE);
            mSearch.setClickable(false);
        }
    }

    @Override
    public void hideAndUnclickableAdd() {
        if(mAdd.getVisibility()==View.VISIBLE){
            mAdd.setVisibility(View.INVISIBLE);
            mAdd.setClickable(false);
        }
    }

    @Override
    public void showAndClickableSearch() {
        if(mSearch.getVisibility()==View.INVISIBLE){
            mSearch.setVisibility(View.VISIBLE);
            mSearch.setClickable(true);
        }
    }

    @Override
    public void showAndClickableAdd() {
        if(mAdd.getVisibility()==View.INVISIBLE){
            mAdd.setVisibility(View.VISIBLE);
            mAdd.setClickable(true);
        }
    }

    @OnClick(R.id.add)
    public void setmAdd() {
        refreshLibraryRushes();
    }

    @OnClick(R.id.search)
    public void setmSearch() {
        startActivity(SearchActivity.getStartIntent(this));
    }

    @OnClick(R.id.library)
    public void setmLibrary() {
        setBottomNavigationTint(0);
        setLibraryFragment();
        showAndClickableAdd();
        showAndClickableSearch();
    }

    @OnClick(R.id.discover)
    public void setmDiscover() {
        setBottomNavigationTint(1);
        setDiscoverFragment();
        hideAndUnclickableAdd();
        showAndClickableSearch();
    }

    @OnClick(R.id.profile)
    public void setmProfile() {
        setBottomNavigationTint(2);
        setProfileFragment();
        hideAndUnclickableAdd();
        hideAndUnclickableSearch();
    }
}
