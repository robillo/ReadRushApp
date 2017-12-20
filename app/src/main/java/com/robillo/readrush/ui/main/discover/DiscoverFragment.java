package com.robillo.readrush.ui.main.discover;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.CollectionListItem;
import com.robillo.readrush.data.network.retrofit.model.CollectionListItemSuper;
import com.robillo.readrush.data.network.retrofit.model.CollectionUnit;
import com.robillo.readrush.data.network.retrofit.model.CollectionsSuper;
import com.robillo.readrush.data.network.retrofit.model.Cover;
import com.robillo.readrush.data.network.retrofit.model.CoverSuper;
import com.robillo.readrush.data.network.retrofit.model.Featured;
import com.robillo.readrush.data.network.retrofit.model.FeaturedSuper;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.discover.PagerFragment.PagerFragment;
import com.robillo.readrush.ui.main.discover.adapters.CollectionListAdapter;
import com.robillo.readrush.ui.main.discover.adapters.CollectionsAdapter;
import com.robillo.readrush.ui.main.discover.adapters.FeaturedAdapter;
import com.robillo.readrush.utils.other_helper.StartSnapHelper;
import com.robillo.readrush.utils.page_transforms.ZoomOutPageTransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends BaseFragment implements DiscoverMvpView {

    List<Featured> mFeatureList = new ArrayList<>();
    List<CollectionUnit> mCollectionList = new ArrayList<>();
    List<CollectionListItem> mCollectionListItems = new ArrayList<>();
    CollectionsAdapter mCollectionAdapter;
    FeaturedAdapter mFeatureAdapter;
    CollectionListAdapter mCollectionListAdapter;
    SnapHelper featureSnapHelper, collectionsSnapHelper;
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @BindView(R.id.pager)
    ViewPager mPager;

    @BindView(R.id.featured)
    RecyclerView mFeatureRv;

    @BindView(R.id.collections_list)
    RecyclerView mCollectionListRv;

    @BindView(R.id.collections)
    RecyclerView mCollectionRv;

    @BindView(R.id.progress_bar_covers)
    ProgressBar mProgressCovers;

    @BindView(R.id.progress_bar_featured)
    ProgressBar mProgressFeatured;

    @BindView(R.id.progress_bar_collections)
    ProgressBar mProgressCollections;

    @BindView(R.id.collections_back_drawable)
    ImageView mCollectionsBackDrawable;

    @Inject
    DiscoverMvpPresenter<DiscoverMvpView> mPresenter;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_discover, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

            component.inject(DiscoverFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

            mPresenter.onAttach(DiscoverFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {

        featureSnapHelper = new StartSnapHelper();
        collectionsSnapHelper = new StartSnapHelper();

        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(getActivity(), ReadRushApp.PREF_FILE_NAME);
        mApiService = ApiClient.getClient().create(ApiInterface.class);

        //SETTING PAGER
        mPager.setClipToPadding(false);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.addOnPageChangeListener(viewPagerPageChangeListener);
        fetchTopCoverBooks();

        //SETTING FEATURE RV
        mFeatureRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        fetchFeaturedBooks();

        //SETTING COLLECTION ADAPTER
        mCollectionRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        fetchCollectionNames();
    }

    @Override
    public void fetchFeaturedBooks() {

        retrofit2.Call<FeaturedSuper> call = mApiService.getFeaturedBooks(mPrefsHelper.getUserId());
        if(call!=null){
            call.enqueue(new Callback<FeaturedSuper>() {
                @Override
                public void onResponse(@NonNull retrofit2.Call<FeaturedSuper> call, @NonNull Response<FeaturedSuper> response) {
                    //noinspection ConstantConditions
                    if(response.body().getMessage()!=null){
                        //noinspection ConstantConditions
                        mFeatureList = response.body().getMessage();
                        if(mFeatureRv!=null){
                            mFeatureRv.setVisibility(View.VISIBLE);
                            mFeatureAdapter = new FeaturedAdapter(mFeatureList, getActivity());
                            mFeatureRv.setAdapter(mFeatureAdapter);
                            mFeatureRv.setOnFlingListener(null);
                            featureSnapHelper.attachToRecyclerView(mFeatureRv);
                        }
                        if(mProgressFeatured!=null) mProgressFeatured.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(@NonNull retrofit2.Call<FeaturedSuper> call, @NonNull Throwable t) {
                    if(getActivity()!=null){
                        Toast.makeText(getActivity(), "Failed to fetch Featured Books", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void fetchCollectionNames() {

        retrofit2.Call<CollectionsSuper> call = mApiService.getCollections(mPrefsHelper.getUserId());
        if(call!=null){
            call.enqueue(new Callback<CollectionsSuper>() {
                @Override
                public void onResponse(@NonNull retrofit2.Call<CollectionsSuper> call, @NonNull Response<CollectionsSuper> response) {
                    //noinspection ConstantConditions
                    if(response.body().getMessage()!=null){
                        //noinspection ConstantConditions
                        mCollectionList = response.body().getMessage();
                        if(mCollectionRv!=null){
                            mCollectionRv.setVisibility(View.VISIBLE);
                            mCollectionAdapter = new CollectionsAdapter(mCollectionList, getActivity(), DiscoverFragment.this);
                            mCollectionRv.setAdapter(mCollectionAdapter);
                            mCollectionRv.setOnFlingListener(null);
                            collectionsSnapHelper.attachToRecyclerView(mCollectionRv);
                        }
                        if(mProgressCollections!=null) mProgressCollections.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(@NonNull retrofit2.Call<CollectionsSuper> call, @NonNull Throwable t) {
                    if(getActivity()!=null){
                        Toast.makeText(getActivity(), "Failed to fetch Collections", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void fetchTopCoverBooks() {

        retrofit2.Call<CoverSuper> call = mApiService.getTopCovers(mPrefsHelper.getUserId());
        if(call!=null){
            call.enqueue(new Callback<CoverSuper>() {
                @Override
                public void onResponse(@NonNull retrofit2.Call<CoverSuper> call, @NonNull Response<CoverSuper> response) {
                    if(mProgressCovers!=null) mProgressCovers.setVisibility(View.GONE);
                    if(mPager!=null){
                        mPager.setVisibility(View.VISIBLE);
                        //noinspection ConstantConditions
                        PagerAdapter adapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(), response.body().getMessage());
                        mPager.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(@NonNull retrofit2.Call<CoverSuper> call, @NonNull Throwable t) {
                    if(getActivity()!=null){
                        Toast.makeText(getActivity(), "Failed to fetch Top Covers", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        List<Cover> mList = new ArrayList<>();
        private int mNumPages = 0;

        ScreenSlidePagerAdapter(FragmentManager fm, @SuppressWarnings("ConstantConditions") List<Cover> mList) {
            super(fm);
            this.mList = mList;
            mNumPages = mList.size();
        }

        @Override
        public Fragment getItem(int position) {

            //POSITION STARTING FROM INDEX 0
            Bundle args = new Bundle();
            args.putString("cover_image", mList.get(position).getCover_image());
            args.putString("rush_id", mList.get(position).getRush_id());
            return PagerFragment.newInstance(args);

        }

        @Override
        public int getCount() {
            return mNumPages;
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

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    @OnClick(R.id.collections_back_drawable)
    public void setmCollectionsBackDrawable() {
        mCollectionRv.setVisibility(View.VISIBLE);
        mCollectionListRv.setVisibility(View.GONE);
        mCollectionsBackDrawable.setVisibility(View.GONE);
    }

    @Override
    public void fetchCollectionFromCid(String coll_id) {
        mCollectionRv.setVisibility(View.GONE);
        mProgressCollections.setVisibility(View.VISIBLE);
        mCollectionsBackDrawable.setVisibility(View.VISIBLE);
        retrofit2.Call<CollectionListItemSuper> call = mApiService.getCollectionFromCid(coll_id);
        if(call!=null){
            call.enqueue(new Callback<CollectionListItemSuper>() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onResponse(@NonNull retrofit2.Call<CollectionListItemSuper> call, @NonNull Response<CollectionListItemSuper> response) {
                    if(response.body().getMessage()!=null){
                        mCollectionListItems = response.body().getMessage();
                        mCollectionListAdapter = new CollectionListAdapter(mCollectionListItems, getActivity());
                        mCollectionListRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        mCollectionListRv.setAdapter(mCollectionListAdapter);
                        mCollectionListRv.setVisibility(View.VISIBLE);
                        mProgressCollections.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(@NonNull retrofit2.Call<CollectionListItemSuper> call, @NonNull Throwable t) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
