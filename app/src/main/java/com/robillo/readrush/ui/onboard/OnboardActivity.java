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
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.login.LoginActivity;
import com.robillo.readrush.ui.onboard.Fragment.OnboardFragment;
import com.robillo.readrush.utils.page_transforms.AccordianTransformer;
import com.robillo.readrush.utils.page_transforms.CubeOutTransformer;
import com.robillo.readrush.utils.page_transforms.ZoomOutPageTransformer;
import com.robillo.readrush.utils.page_transforms.ZoomOutTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnboardActivity extends BaseActivity implements OnboardMvpView {

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
        mPager.addOnPageChangeListener(viewPagerPageChangeListener);
        previous.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void goPrevious() {
        mPager.setCurrentItem(mPager.getCurrentItem()-1);
    }

    @Override
    public void goNext() {
        if(mPager.getCurrentItem()!=NUM_PAGES-1){
            mPager.setCurrentItem(mPager.getCurrentItem()+1);
        }
        else {
            //start login activity
            startActivity(LoginActivity.getStartIntent(this));
        }
    }

    @OnClick(R.id.next)
    public void setNext(){
        goNext();
    }

    @OnClick(R.id.prev)
    public void setPrevious(){
        goPrevious();
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
                    return OnboardFragment.newInstance(R.drawable.one, getString(R.string.dummy_header1), "No two persons ever read the same book.");
                }
                case 1:{
                    return OnboardFragment.newInstance(R.drawable.four, getString(R.string.dummy_header2), "Whenever you read a good book, somewhere in the world a door opens to allow in more light.");
                }
                case 2:{
                    return OnboardFragment.newInstance(R.drawable.three, getString(R.string.dummy_header3), "If we encounter a man of rare intellect, we should ask him what books he reads.");
                }
                case 3:{
                    return OnboardFragment.newInstance(R.drawable.four, getString(R.string.dummy_header4), "I would never read a book if it were possible for me to talk half an hour with the man who wrote it.");
                }
                default:{
                    return OnboardFragment.newInstance(R.drawable.four, getString(R.string.dummy_header5), "I would never read a book if it were possible for me to talk half an hour with the man who wrote it.");
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

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

//            addBottomDots(position);

            if(position==0 && previous.isEnabled()){
                previous.setEnabled(false);
            }
            else {
                if(!previous.isEnabled()){
                    previous.setEnabled(true);
                }
            }

            if(position == NUM_PAGES-1){
                next.setText(R.string.finish);
            }

            if(position!=NUM_PAGES-1 && next.getText().equals("Finish")){
                next.setText(getString(R.string.next));
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
}
