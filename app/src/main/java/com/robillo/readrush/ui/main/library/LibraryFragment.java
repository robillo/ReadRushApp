package com.robillo.readrush.ui.main.library;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.model.Progress;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.db.model.library.LibraryCover;
import com.robillo.readrush.data.db.model.library.LibraryCoverRepository;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.LibraryItem;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.onboard.fragment.OnboardFragment;
import com.robillo.readrush.ui.rushoverview.OverviewActivity;
import com.robillo.readrush.ui.rushread.ReadRushActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends BaseFragment implements LibraryMvpView {

    public static int RUSH_COUNT = 0;

    //online
    private List<LibraryItem> mLibraryItemList;

    //offline
    LiveData<List<LibraryCover>> mListLibraryCovers;
    List<LibraryCover> mCoversList;

    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @BindView(R.id.error_layout)
    LinearLayout mErrorLayout;

    @BindView(R.id.main_layout)
    LinearLayout mMainLayout;

    @BindView(R.id.error_drawable)
    ImageView mErrorDrawable;

    @BindView(R.id.error_header)
    TextView mErrorHeader;

    @BindView(R.id.error_description)
    TextView mErrorDescription;

    @BindView(R.id.rush1)
    ImageView mRushOne;

    @BindView(R.id.rush2)
    ImageView mRushTwo;

    @BindView(R.id.rush3)
    ImageView mRushThree;

    @BindView(R.id.rush4)
    ImageView mRushFour;

    @BindView(R.id.rush5)
    ImageView mRushFive;

    @BindView(R.id.rush6)
    ImageView mRushSix;

    @BindView(R.id.rush7)
    ImageView mRushSeven;

    @BindView(R.id.rush8)
    ImageView mRushEight;

    @BindView(R.id.rush9)
    ImageView mRushNine;

    @BindView(R.id.refresh_buttom)
    Button mRefreshButton;

    @BindView(R.id.progress_bar_library)
    ProgressBar mProgressLibrary;

    @Inject
    LibraryCoverRepository mLibraryCoverRepository;

    @Inject
    LibraryMvpPresenter<LibraryMvpView> mPresenter;

    public LibraryFragment() {
        // Required empty public constructor
    }

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_library, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(LibraryFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(LibraryFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(getActivity(), ReadRushApp.PREF_FILE_NAME);
        mApiService = ApiClient.getClient().create(ApiInterface.class);

        mListLibraryCovers = mLibraryCoverRepository.getAllCovers();

        checkForExistingRushesOffline();
    }

    @Override
    public void loadRushes() {
        if(mMainLayout.getVisibility()==View.GONE) mMainLayout.setVisibility(View.VISIBLE);
        if(mErrorLayout.getVisibility()==View.VISIBLE) mErrorLayout.setVisibility(View.GONE);
        if(mProgressLibrary.getVisibility()==View.VISIBLE) mProgressLibrary.setVisibility(View.GONE);

        loadCoversIntoRushes();
    }

    @Override
    public void checkForExistingRushesOnline() {

        Call<List<LibraryItem>> call = mApiService.fetchLibrary(mPrefsHelper.getUserId());
//        Call<List<LibraryItem>> call = mApiService.fetchLibrary("1");
        if(call!=null){
            call.enqueue(new Callback<List<LibraryItem>>() {
                @Override
                public void onResponse(@NonNull Call<List<LibraryItem>> call, @NonNull Response<List<LibraryItem>> response) {
                    mLibraryItemList = response.body();
                    if(mLibraryItemList!=null){
                        if(mProgressLibrary!=null) mProgressLibrary.setVisibility(View.GONE);
                        if(mMainLayout!=null) mMainLayout.setVisibility(View.VISIBLE);
                        if(mErrorLayout!=null) mErrorLayout.setVisibility(View.GONE);
                    }
                    else {
                        if(mMainLayout!=null) mMainLayout.setVisibility(View.INVISIBLE);
                        if(mProgressLibrary!=null) mProgressLibrary.setVisibility(View.GONE);
                        if(mErrorLayout!=null) mErrorLayout.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<LibraryItem>> call, @NonNull Throwable t) {
                    if(mProgressLibrary!=null) mProgressLibrary.setVisibility(View.GONE);
                    if(mErrorLayout!=null) mErrorLayout.setVisibility(View.VISIBLE);
                    if(mMainLayout!=null) mMainLayout.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void checkForExistingRushesOffline() {
        mListLibraryCovers.observe(this, new Observer<List<LibraryCover>>() {
            @Override
            public void onChanged(@Nullable List<LibraryCover> libraryCovers) {
                mCoversList = libraryCovers;
                if(verifyOfflineRushesExist()){
                    //SHOW THE LIBRARY AND DONT SHOW THE ERROR MESSAGE
                    loadRushes();
                }
                else {
                    //SHOW ERROR MESSAGE AND ASK FOR AN ONLINE REFRESH
                }
            }
        });
    }

    @Override
    public boolean verifyOfflineRushesExist() {
        return !(mCoversList == null) && mCoversList.size() > 0;
    }

    @Override
    public void showNoRushes() {
        if(mErrorLayout.getVisibility()==View.GONE){
            mErrorLayout.setVisibility(View.VISIBLE);
        }
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
        mErrorDrawable.setAnimation(animation);
        mErrorDescription.setAnimation(animation);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
        mErrorHeader.setAnimation(animation);
    }

    @Override
    public void loadCoversIntoRushes() {
        int count = mCoversList.size();
        if(count>0 && mCoversList.get(0).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(0).getCover(), mRushOne);
        if(count>1 && mCoversList.get(1).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(1).getCover(), mRushTwo);
        if(count>2 && mCoversList.get(2).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(2).getCover(), mRushThree);
        if(count>3 && mCoversList.get(3).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(3).getCover(), mRushFour);
        if(count>4 && mCoversList.get(4).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(4).getCover(), mRushFive);
        if(count>5 && mCoversList.get(5).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(5).getCover(), mRushSix);
        if(count>6 && mCoversList.get(6).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(6).getCover(), mRushSeven);
        if(count>7 && mCoversList.get(7).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(7).getCover(), mRushEight);
        if(count>8 && mCoversList.get(8).getCover()!=null) loadSingleCoverIntoSingleRush(mCoversList.get(8).getCover(), mRushNine);
    }

    @Override
    public void loadSingleCoverIntoSingleRush(String cover_url, ImageView rush) {
        Glide.with(getActivity()).load(cover_url).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().centerCrop().into(rush);
    }

    @Override
    public void openReadRushScreen(int index) {
        int count = mCoversList.size();
        if(count > index) startActivity(ReadRushActivity.getStartIntent(getActivity(), mCoversList.get(index).getRushId()));
        else Toast.makeText(getActivity(), "Empty Cover", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.rush1)
    public void clickmRushOne() {
        openReadRushScreen(0);
    }

    @OnClick(R.id.rush2)
    public void clickmRushTwo() {
        openReadRushScreen(1);
    }

    @OnClick(R.id.rush3)
    public void clickmRushThree() {
        openReadRushScreen(2);
    }
    @OnClick(R.id.rush4)
    public void clickmRushFour() {
        openReadRushScreen(3);
    }

    @OnClick(R.id.rush5)
    public void clickmRushFive() {
        openReadRushScreen(4);
    }

    @OnClick(R.id.rush6)
    public void clickmRushSix() {
        openReadRushScreen(5);
    }

    @OnClick(R.id.rush7)
    public void clickmRushSeven() {
        openReadRushScreen(6);
    }

    @OnClick(R.id.rush8)
    public void clickmRushEight() {
        openReadRushScreen(7);
    }

    @OnClick(R.id.rush9)
    public void clickmRushNine() {
        openReadRushScreen(8);
    }

    @OnClick(R.id.refresh_buttom)
    public void setmRefreshButton(){
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.refreshLibraryRushes();
        }
    }
}
