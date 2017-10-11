package com.robillo.readrush.ui.onboard;

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
import com.robillo.readrush.utils.page_transforms.AccordianTransformer;
import com.robillo.readrush.utils.page_transforms.CubeOutTransformer;
import com.robillo.readrush.utils.page_transforms.ZoomOutPageTransformer;
import com.robillo.readrush.utils.page_transforms.ZoomOutTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardActivity extends BaseActivity implements OnboardMvpView{

    private static final int NUM_PAGES = 4;

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
        mPager.setPageTransformer(true, new CubeOutTransformer());
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
                    return OnboardFragment.newInstance(R.drawable.one, "Same Book", "No two persons ever read the same book.");
                }
                case 1:{
                    return OnboardFragment.newInstance(R.drawable.four, "Opening Door", "Whenever you read a good book, somewhere in the world a door opens to allow in more light.");
                }
                case 2:{
                    return OnboardFragment.newInstance(R.drawable.three, "Intellectual Man", "If we encounter a man of rare intellect, we should ask him what books he reads.");
                }
                case 3:{
                    return OnboardFragment.newInstance(R.drawable.four, "Half an hour", "I would never read a book if it were possible for me to talk half an hour with the man who wrote it.");
                }
                default:{
                    return OnboardFragment.newInstance(R.drawable.four, "Half an hour", "I would never read a book if it were possible for me to talk half an hour with the man who wrote it.");
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
