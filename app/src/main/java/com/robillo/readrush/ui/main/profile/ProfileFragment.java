package com.robillo.readrush.ui.main.profile;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.db.model.library.LibraryCover;
import com.robillo.readrush.data.db.model.library.LibraryCoverRepository;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.ProfileNumbers;
import com.robillo.readrush.data.network.retrofit.model.ProfileNumbersSuper;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.profile.profile_covers_adapter.ProfileCover;
import com.robillo.readrush.ui.main.profile.profile_covers_adapter.ProfileCoverAdapter;
import com.robillo.readrush.ui.settings.SettingsActivity;

import java.util.ArrayList;
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
public class ProfileFragment extends BaseFragment implements ProfileMvpView {

    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;
    private AppPreferencesHelper mPrefsHelper;

    //offline
    LiveData<List<LibraryCover>> mListLibraryCovers;
    List<LibraryCover> mCoversList;
    List<ProfileNumbers> mProfileReadingCovers;

    //online
    List<ProfileNumbers> mProfileReadCovers;

    @BindView(R.id.name)
    TextView mUserName;

    @BindView(R.id.membership_type)
    TextView mMembershipType;

//    @BindView(R.id.profile)
//    Button mProfileTab;
//
//    @BindView(R.id.highlights)
//    Button mHighlightsTab;
//
//    @BindView(R.id.profile_container)
//    FrameLayout mFragmentContainer;

    @BindView(R.id.settings)
    ImageView mSettings;

    @BindView(R.id.read)
    TextView mRead;

    @BindView(R.id.reading)
    TextView mReading;

    @BindView(R.id.progress_bar_read_rv)
    ProgressBar mProgressRead;

    @BindView(R.id.progress_bar_reading_rv)
    ProgressBar mProgressReading;

    @BindView(R.id.read_rv)
    RecyclerView mReadRecycler;

    @BindView(R.id.reading_rv)
    RecyclerView mReadINGRecycler;

    @Inject
    ProfileMvpPresenter<ProfileMvpView> mPresenter;

    @Inject
    LibraryCoverRepository mLibraryCoverRepository;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(ProfileFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(ProfileFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {

        mListLibraryCovers = mLibraryCoverRepository.getAllCovers();

        if(getActivity()!=null)
            mPrefsHelper = new AppPreferencesHelper(getActivity(), ReadRushApp.PREF_FILE_NAME);
        mApiService = ApiClient.getClient().create(ApiInterface.class);

        mUserName.setText(mPrefsHelper.getUserName());

        mReadINGRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mReadRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        checkForExistingRushesOffline();
        callForReadRushes();
    }

    @OnClick(R.id.settings)
    public void setmSettings() {
        startActivity(SettingsActivity.getStartIntent(getActivity()));
    }

    @Override
    public void loadRushes() {
        //Inflate reading recyclerView with mCoversList covers
        mProfileReadingCovers = new ArrayList<>();
        for(int i=0; i<mCoversList.size(); i++){
            mProfileReadingCovers.add(new ProfileNumbers(mCoversList.get(i).getRushId(), mCoversList.get(i).getTitle(), mCoversList.get(i).getCover()));
        }
        ProfileCoverAdapter adapter = new ProfileCoverAdapter(mProfileReadingCovers, getActivity());
        mReadINGRecycler.setAdapter(adapter);
    }

    @Override
    public void checkForExistingRushesOffline() {
        mListLibraryCovers.observe(this, new Observer<List<LibraryCover>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(@Nullable List<LibraryCover> libraryCovers) {
                mCoversList = libraryCovers;
                if(verifyOfflineRushesExist()){
                    //SHOW THE LIBRARY AND DONT SHOW THE ERROR MESSAGE
                    if(mProgressReading!=null) mProgressReading.setVisibility(View.GONE);
                    if(mReadINGRecycler!=null) mReadINGRecycler.setVisibility(View.VISIBLE);
                    mReading.setText("Reading: " + mCoversList.size());
                    loadRushes();
                }
                else {
                    if(mProgressReading!=null) mProgressReading.setVisibility(View.GONE);
                    if(mReadINGRecycler!=null) mReadINGRecycler.setVisibility(View.GONE);
                    mReading.setText(R.string.reading_zero);
                    Toast.makeText(getActivity(), "No rushes currently being read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean verifyOfflineRushesExist() {
        return !(mCoversList == null) && mCoversList.size() > 0;
    }

    @Override
    public void callForReadRushes() {
        Call<ProfileNumbersSuper> call = mApiService.fetchProfileNumbers(mPrefsHelper.getUserId());
        if(call!=null)
            call.enqueue(new Callback<ProfileNumbersSuper>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<ProfileNumbersSuper> call, @NonNull Response<ProfileNumbersSuper> response) {
                    //noinspection ConstantConditions
                    if(response.body()!=null && response.body().getRushes_read()!=null){
                        //noinspection ConstantConditions
                        mProfileReadCovers = response.body().getRushes_read();
                        ProfileCoverAdapter adapter = new ProfileCoverAdapter(mProfileReadCovers, getActivity());
                        mReadRecycler.setAdapter(adapter);
                        //hide progress, show and inflate rv, and increase count of textView
                        if(mProgressRead!=null) mProgressRead.setVisibility(View.GONE);
                        if(mReadRecycler!=null) mReadRecycler.setVisibility(View.VISIBLE);
                        //noinspection ConstantConditions
                        mRead.setText("Reading: " + response.body().getRushes_read().size());
                    }
                    else {
                        //hide progress and set count of textView as zero
                        if(mProgressRead!=null) mProgressRead.setVisibility(View.GONE);
                        if(mReadRecycler!=null) mReadRecycler.setVisibility(View.GONE);
                        mRead.setText(R.string.read_zero);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ProfileNumbersSuper> call, @NonNull Throwable t) {
                    if(mProgressRead!=null) mProgressRead.setVisibility(View.GONE);
                    if(mReadRecycler!=null) mReadRecycler.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Failed to establish network connection", Toast.LENGTH_SHORT).show();
                }
            });
    }

//    @OnClick(R.id.profile)
//    public void setmProfileTab() {
//        setProfileListFragment();
//    }
//
//    @OnClick(R.id.highlights)
//    public void setmHighlightsTab() {
//        setHighlightsListFragment();
//    }

//    @Override
//    public void setProfileListFragment() {
//        if(getActivity()!=null)
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction().replace(R.id.profile_container, new ProfileListFragment(), "profile_list")
//                    .commit();
//    }
//
//    @Override
//    public void setHighlightsListFragment() {
//        if(getActivity()!=null)
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction().replace(R.id.profile_container, new HighlightsListFragment(), "highlights_list")
//                    .commit();
//    }
}
