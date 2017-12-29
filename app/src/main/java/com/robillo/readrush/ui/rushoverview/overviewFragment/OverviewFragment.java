package com.robillo.readrush.ui.rushoverview.overviewFragment;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.db.model.library.LibraryContentRepository;
import com.robillo.readrush.data.db.model.library.LibraryCover;
import com.robillo.readrush.data.db.model.library.LibraryCoverContent;
import com.robillo.readrush.data.db.model.library.LibraryCoverRepository;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.Content;
import com.robillo.readrush.data.network.retrofit.model.RushInfo;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsFragment;
import com.robillo.readrush.ui.rushread.ReadRushActivity;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.supercharge.shimmerlayout.ShimmerLayout;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ConstantConditions")
public class OverviewFragment extends BaseFragment implements OverviewFragmentMvpView {

    static String mRushId;
    static boolean mRushAudio = false;
    private RushInfo mRushInfo;
    LiveData<List<String>> mLibraryCoversRushIds;
    LiveData<List<LibraryCoverContent>> mLibraryCoverContents;
    List<LibraryCoverContent> mLibraryCoverContentsList = new ArrayList<>();
    List<String> mRushIds;
    private List<Content> mContents;
    LibraryCover mRoomCover = null;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @Inject
    LibraryCoverRepository mLibraryCoverRepository;

    @Inject
    LibraryContentRepository mLibraryContentRepository;

    @Inject
    OverviewFragmentMvpPresenter<OverviewFragmentMvpView> mPresenter;

    @BindView(R.id.scale_rating_bar)
    ScaleRatingBar mRatingBar;

    @BindView(R.id.exit)
    ImageView mExit;

    @BindView(R.id.shimmer)
    ShimmerLayout mShimmerLayout;

    @BindView(R.id.cover)
    ImageView mCover;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.author)
    TextView mAuthor;

    @BindView(R.id.description)
    TextView mDescription;

    @BindView(R.id.reviews)
    Button mReviews;

    @BindView(R.id.add_read_rush)
    Button mAddReadRush;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(Bundle bundle) {
        OverviewFragment fragment = new OverviewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_overview, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(OverviewFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(OverviewFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {

        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(getActivity(), ReadRushApp.PREF_FILE_NAME);
        mLibraryCoversRushIds = mLibraryCoverRepository.getAllRushIds();
        mLibraryCoverContents = mLibraryCoverRepository.getContentsForRushID(mRushId);
        fetchListMyLibRushIds();
        mApiService = ApiClient.getClient().create(ApiInterface.class);

        //noinspection ConstantConditions
        mRushId = getArguments().getString("rush_id");
        mShimmerLayout.startShimmerAnimation();

        mLibraryCoverContents.observe(this, new Observer<List<LibraryCoverContent>>() {
            @Override
            public void onChanged(@Nullable List<LibraryCoverContent> libraryCoverContents) {
                mLibraryCoverContentsList = libraryCoverContents;
            }
        });

        fetchRushDetails();
    }

    @Override
    public void setReviewsFragment() {
        //noinspection ConstantConditions
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.review_enter_anim, R.anim.review_exit_anim);
        Bundle args = new Bundle();
        args.putString("rush_id", mRushId);
        transaction.add(R.id.container, ReviewsFragment.newInstance(args), "review").commit();
    }

    @Override
    public void fetchRushDetails() {
        Call<List<RushInfo>> call = mApiService.fetchRush(mRushId);
        if(call!=null){
            call.enqueue(new Callback<List<RushInfo>>() {
                @Override
                public void onResponse(@NonNull Call<List<RushInfo>> call, @NonNull Response<List<RushInfo>> response) {

                    if(!verifyRushExistsInMyLib()){
                        if(mAddReadRush!=null) mAddReadRush.setText(R.string.add_rush);
                    }
                    else {
                        if(mAddReadRush!=null) mAddReadRush.setText(R.string.read_rush);
                    }

                    //noinspection ConstantConditions
                    mRushInfo = response.body().get(0);

                    if(mRushInfo.getRush_id()!=null){
                        mRoomCover = new LibraryCover(mRushInfo.getRush_id(), mRushInfo.getTitle(), mRushInfo.getAuthor(), mRushInfo.getRating(), mRushInfo.getEst_time(), mRushInfo.getPages(), mRushInfo.getCover(), null, mRushInfo.getAudio()!=null);
                    }

                    if(mShimmerLayout!=null) mShimmerLayout.stopShimmerAnimation();
                    if(mName!=null) {
                        Glide.with(getActivity()).load(mRushInfo.getCover()).centerCrop().into(mCover);
                        mName.setText(mRushInfo.getTitle());
                        mAuthor.setText(mRushInfo.getAuthor());
                        mDescription.setText(mRushInfo.getDescription());
                        mAddReadRush.setClickable(true);
                        mAddReadRush.setVisibility(View.VISIBLE);
                        mReviews.setClickable(true);
                        mReviews.setVisibility(View.VISIBLE);
                        mRatingBar.setRating(Float.valueOf(mRoomCover.getRating()));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<RushInfo>> call, @NonNull Throwable t) {
                    if(getActivity()!=null) Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void fetchListMyLibRushIds() {
        mLibraryCoversRushIds.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                mRushIds = strings;
            }
        });
    }

    @Override
    public boolean verifyRushExistsInMyLib() {
        boolean verified = false;
        if(!(mRushIds ==null)){
            for(int i=0; i<mRushIds.size(); i++) {
                if(mRushIds.get(i).equals(mRushId)){
                    verified = true;
                }
            }
        }
        return verified;
    }

    @Override
    public void addRushToOnlineLibrary() {
        Call<ResponseBody> call = mApiService.addToUserLibrary(mPrefsHelper.getUserId(), mRushId);
        if(call!=null){
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                    Toast.makeText(getActivity(), "Successfully Added To Cloud Library", Toast.LENGTH_SHORT).show();

                    //Add to user offline library
                    mLibraryCoverRepository.insertCoverItem(mRoomCover);
                    mAddReadRush.setText(getString(R.string.read_rush));
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick(R.id.reviews)
    public void seeReviews() {
        setReviewsFragment();
    }

    @OnClick(R.id.exit)
    public void setmExit(){
        if(getActivity()!=null){
            getActivity().onBackPressed();
        }
    }

    @OnClick(R.id.add_read_rush)
    public void setmAddReadRush() {
        if(mAddReadRush.getText().equals(getString(R.string.add_rush))){
            addRushToOnlineLibrary();
        }
        else if(mAddReadRush.getText().equals(getString(R.string.read_rush))){
            if(mLibraryCoverContentsList.size()>0){
                startActivity(ReadRushActivity.getStartIntent(getActivity(), mRushId, mRushAudio, mName.getText().toString()));
            }
            else {
                getContent();
            }
        }
    }

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
                        for(int i=0; i<mContents.size(); i++){
                            LibraryCoverContent coverContent = new LibraryCoverContent
                                    (mContents.get(i).getContent_id(), mContents.get(i).getRush_id(),
                                            mContents.get(i).getContent(), mContents.get(i).getAttr(),
                                            mContents.get(i).getDatetime(), mContents.get(i).getPage_no());
                            mLibraryContentRepository.insertContentItem(coverContent);
                        }
//
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Content>> call, @NonNull Throwable t) {
                    if(getActivity()!=null) Toast.makeText(getActivity(), "Network Error while downloading rush content", Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                }
            });
        }
    }
}
