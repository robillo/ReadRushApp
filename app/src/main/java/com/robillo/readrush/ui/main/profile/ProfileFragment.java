package com.robillo.readrush.ui.main.profile;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.db.model.library.LibraryCover;
import com.robillo.readrush.data.db.model.library.LibraryCoverRepository;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;
import com.robillo.readrush.ui.main.discover.adapters.FeaturedAdapter;
import com.robillo.readrush.ui.main.profile.highlights_list.HighlightsListFragment;
import com.robillo.readrush.ui.main.profile.profile_list.ProfileListFragment;
import com.robillo.readrush.ui.settings.SettingsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
        checkForExistingRushesOffline();
    }

    @OnClick(R.id.settings)
    public void setmSettings() {
        startActivity(SettingsActivity.getStartIntent(getActivity()));
    }

    @Override
    public void loadRushes() {
        //Inflate reading recyclerView with mCoversList covers
        mReadINGRecycler.setVisibility(View.VISIBLE);
//        mFeatureAdapter = new FeaturedAdapter(mFeatureList, getActivity());
//        mReadINGRecycler.setAdapter(mFeatureAdapter);
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
