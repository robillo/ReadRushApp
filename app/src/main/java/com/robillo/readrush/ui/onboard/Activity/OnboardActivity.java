package com.robillo.readrush.ui.onboard.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.Button;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.onboard.Fragment.OnboardFragment;
import com.robillo.readrush.ui.splash.SplashActivity;
import com.robillo.readrush.utils.ZoomOutPageTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardActivity extends BaseActivity implements OnboardMvpView{

    private static final int NUM_PAGES = 3;

    @Inject
    OnboardMvpPresenter<OnboardMvpView> mPresenter;

    @BindView(R.id.prev)
    Button previous;

    @BindView(R.id.next)
    Button next;

    @BindView(R.id.pager)
    ViewPager mPager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, OnboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        getActivityComponent().inject(OnboardActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(OnboardActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        PagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setClipToPadding(false);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:{
                    return OnboardFragment.newInstance(R.drawable.one, "one", "one one one one");
                }
                case 1:{
                    return OnboardFragment.newInstance(R.drawable.two, "two", "two two two two");
                }
                case 2:{
                    return OnboardFragment.newInstance(R.drawable.three, "three", "three three three three");
                }
                default:{
                    return OnboardFragment.newInstance(R.drawable.four, "four", "four four four four");
                }
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public float getPageWidth(int position) {
            return 1.0f;
        }

    }
}
