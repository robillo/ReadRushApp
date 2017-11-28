package com.robillo.readrush.ui.rushoverview.overviewFragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.RushInfo;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;
import com.robillo.readrush.ui.main.discover.PagerFragment.PagerFragment;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsFragment;

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
public class OverviewFragment extends BaseFragment implements OverviewFragmentMvpView {

    static String mRushId;
    private RushInfo mRushInfo;

    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;


    @Inject
    OverviewFragmentMvpPresenter<OverviewFragmentMvpView> mPresenter;

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
        mApiService = ApiClient.getClient().create(ApiInterface.class);

        //noinspection ConstantConditions
        mRushId = getArguments().getString("rush_id");
        mShimmerLayout.startShimmerAnimation();

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
                    //noinspection ConstantConditions
                    mRushInfo = response.body().get(0);
                    mShimmerLayout.stopShimmerAnimation();
                    Glide.with(getActivity()).load(mRushInfo.getCover()).centerCrop().into(mCover);
                    mName.setText(mRushInfo.getTitle());
                    mAuthor.setText(mRushInfo.getAuthor());
                    mDescription.setText(mRushInfo.getDescription());
                    mAddReadRush.setClickable(true);
                    mAddReadRush.setVisibility(View.VISIBLE);
                    mReviews.setClickable(true);
                    mReviews.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call<List<RushInfo>> call, Throwable t) {
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
}
