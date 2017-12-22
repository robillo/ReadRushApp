package com.robillo.readrush.ui.rushread;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.Content;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.rushread.content.ContentFragment;
import com.robillo.readrush.ui.rushread.rating.RatingFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by robinkamboj on 09/12/17.
 */

public class ReadRushActivity extends BaseActivity implements ReadRushMvpView {

    private int NUM_PAGES = 0;
    private static int mCurrentPage = 0;

    @Inject
    ReadRushMvpPresenter<ReadRushMvpView> mPresenter;

    @BindView(R.id.content_pager)
    ViewPager mContentPager;
    @BindView(R.id.content_progress)
    ProgressBar mContentProgress;
    @BindView(R.id.customize_content_layout)
    LinearLayout mCustomizeLinearLayout;
    @BindView(R.id.text_plus)
    ImageButton mTextviewIncrease;
    @BindView(R.id.text_minus)
    ImageButton mTextviewDecrease;
    @BindView(R.id.launch_audio)
    ImageButton mLaunchAudio;
    @BindView(R.id.text_format_dialog)
    ImageButton mTextFormatDialog;
    @BindView(R.id.content_theme)
    ImageButton mContentTheme;
    @BindView(R.id.line_spacing)
    ImageButton mLineSpacing;
    @BindView(R.id.content_padding)
    ImageButton mContentPadding;

    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;
    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;
    private List<Content> mContents;
    private ScreenSlidePagerAdapter mScreenSlidePagerAdapter;

    String mRushId = null;
    boolean mRushAudio = false;

    public static Intent getStartIntent(Context context, String rush_id, boolean rush_audio) {
        Intent intent = new Intent(context, ReadRushActivity.class);
        intent.putExtra("rush_id", rush_id);
        intent.putExtra("rush_audio", rush_audio);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_rush);

        getActivityComponent().inject(ReadRushActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(ReadRushActivity.this);

        setUp();
    }

    public void setUp() {
        mRushId = getIntent().getStringExtra("rush_id");
        mRushAudio = getIntent().getBooleanExtra("rush_audio", false);

        if(!mRushAudio) mLaunchAudio.setVisibility(View.GONE);

        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
        mScreenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        setInitialTheme();
        getContent();
    }

    @Override
    public void setLightTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.dardRed));
        }
    }

    @Override
    public void setDarkTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.dardReadBlack));
        }
    }

    @Override
    public void getContent() {
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Content>> call = mApiService.getRushContent(mRushId);
        if(call!=null){
            call.enqueue(new Callback<List<Content>>() {
                @Override
                public void onResponse(@NonNull Call<List<Content>> call, @NonNull Response<List<Content>> response) {
                    mContents = response.body();
                    if(mContents!=null && mContents.size()>0){
                        //noinspection ConstantConditions
                        mContentProgress.setMax(response.body().size()+1);
                        mContentProgress.setProgress(1);
                        setFragmentsForContents(mContents);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Content>> call, @NonNull Throwable t) {
                    Toast.makeText(ReadRushActivity.this, "Network Error", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    public void setFragmentsForContents(List<Content> contents) {
        if(contents!=null){
            NUM_PAGES = contents.size()+1;
            mContentPager.addOnPageChangeListener(viewPagerPageChangeListener);
            mContentPager.setAdapter(mScreenSlidePagerAdapter);
            mContentPager.setCurrentItem(mCurrentPage);
        }
    }

    @Override
    public void hideShowCustomizeLayout() {
        if(mCustomizeLinearLayout.getVisibility()== View.VISIBLE){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.customize_bottom_up);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mCustomizeLinearLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mCustomizeLinearLayout.startAnimation(animation);
        }
        else {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.customize_top_down);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mCustomizeLinearLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mCustomizeLinearLayout.startAnimation(animation);
        }
    }

    @Override
    public void hideCustomizeLayout() {
        if(mCustomizeLinearLayout.getVisibility()== View.VISIBLE){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.customize_bottom_up);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mCustomizeLinearLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mCustomizeLinearLayout.startAnimation(animation);
        }
    }

    @Override
    public void showCustomizeLayout() {
        if(mCustomizeLinearLayout.getVisibility()!=View.VISIBLE){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.customize_top_down);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mCustomizeLinearLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mCustomizeLinearLayout.startAnimation(animation);
        }
    }

    @OnClick(R.id.text_plus)
    public void increaseTextSize() {
        mPrefsHelper.setTextSize(mPrefsHelper.getTextSize() + 2);
        refreshFragments();
    }

    @OnClick(R.id.text_minus)
    public void decreaseTextSize() {
        mPrefsHelper.setTextSize(mPrefsHelper.getTextSize() - 2);
        refreshFragments();
    }

//    @OnClick(R.id.text_font)
//    public void changeFont() {
//
//    }

    @OnClick(R.id.content_theme)
    public void changeTheme() {
        if((mPrefsHelper.getAppTheme()).equals("NIGHT")){
            mPrefsHelper.setAppTheme("DAY");
        }
        else {
            mPrefsHelper.setAppTheme("NIGHT");
        }
        setInitialTheme();
        refreshFragments();
        Toast.makeText(this, "CHANGE CONTENT THEME", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.line_spacing)
    public void changeLineSpacing() {
        if(mPrefsHelper.getLineSpacing() == 1.5F){
            mPrefsHelper.setLineSpacing(2.0F);
        }
        else {
            mPrefsHelper.setLineSpacing(1.5F);
        }
        refreshFragments();
    }

    @OnClick(R.id.content_padding)
    public void changeContentPadding() {
        if(mPrefsHelper.getContentPadding() == 60){
            mPrefsHelper.setContentPadding(90);
        }
        else {
            mPrefsHelper.setContentPadding(60);
        }
        refreshFragments();
    }

    @Override
    public void refreshFragments() {
        setFragmentsForContents(mContents);
    }

    @Override
    public void setInitialTheme() {
        if((mPrefsHelper.getAppTheme()).equals("NIGHT")){
            mCustomizeLinearLayout.setBackgroundColor(getResources().getColor(R.color.readBlack));
            mContentProgress.setBackgroundColor(getResources().getColor(R.color.readBlack));
            setDarkTheme();
        }
        else {
            mCustomizeLinearLayout.setBackgroundColor(getResources().getColor(R.color.rushRed));
            mContentProgress.setBackgroundColor(getResources().getColor(R.color.rushRed));
            setLightTheme();
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(mContents.size()>position){
                return ContentFragment.newInstance(mContents.get(position).getContent());
            }
            else {
                //null pointer
                return new RatingFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public float getPageWidth(int position) {
            return 1.0f;
        }

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            mCurrentPage = position;
            mContentProgress.setProgress(position+1);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
}
