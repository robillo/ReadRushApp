package com.robillo.readrush.ui.rushoverview.reviewsFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.Review;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.rushoverview.OverviewActivity;

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
public class ReviewsFragment extends BaseFragment implements ReviewsMvpView {

    ReviewsAdapter mAdapter;
    List<Review> mReviewList = new ArrayList<>();
    private static String mRushId;
    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @BindView(R.id.reviews)
    RecyclerView mReviewRv;

    @BindView(R.id.exit)
    ImageView mExit;

    @Inject
    ReviewsMvpPresenter<ReviewsMvpView> mPresenter;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment newInstance(Bundle bundle) {
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reviews, container, false);

        ActivityComponent component = getActivityComponent();

        if(getActivityComponent()!=null){

            component.inject(ReviewsFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(ReviewsFragment.this);
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
        fetchReviews(mRushId);
    }

    @OnClick(R.id.exit)
    public void setmExit(){
        //noinspection ConstantConditions
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.review_enter_anim, R.anim.review_exit_anim);
        transaction.remove(getActivity().getSupportFragmentManager().findFragmentByTag("review")).commit();
    }

    @Override
    public void fetchReviews(String rushId) {
        Call<List<Review>> call = mApiService.fetchRushReview(mRushId);
        if(call!=null){
            call.enqueue(new Callback<List<Review>>() {
                @Override
                public void onResponse(@NonNull Call<List<Review>> call, @NonNull Response<List<Review>> response) {
                    mReviewList = response.body();
                    mAdapter = new ReviewsAdapter(getActivity(), mReviewList);
                    mReviewRv.setAdapter(mAdapter);
                    mReviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                }

                @Override
                public void onFailure(@NonNull Call<List<Review>> call, @NonNull Throwable t) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
