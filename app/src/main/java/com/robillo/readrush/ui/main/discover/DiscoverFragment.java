package com.robillo.readrush.ui.main.discover;


import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.Featured;
import com.robillo.readrush.data.network.retrofit.model.FeaturedSuper;
import com.robillo.readrush.data.others.Collection;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.discover.PagerFragment.PagerFragment;
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
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends BaseFragment implements DiscoverMvpView {

    static int NUM_PAGES = 3;
    List<Featured> mFeatureList = new ArrayList<>();
    List<Collection> mCollectionList = new ArrayList<>();
    CollectionsAdapter mCollectionAdapter;
    FeaturedAdapter mFeatureAdapter;
    SnapHelper featureSnapHelper, collectionsSnapHelper;
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @BindView(R.id.pager)
    ViewPager mPager;

    @BindView(R.id.featured)
    RecyclerView mFeatureRv;

    @BindView(R.id.collections)
    RecyclerView mCollectionRv;

    @BindView(R.id.progress_bar_covers)
    ProgressBar mProgressCovers;

    @BindView(R.id.progress_bar_featured)
    ProgressBar mProgressFeatured;

    @BindView(R.id.progress_bar_collections)
    ProgressBar mProgressCollections;

    @Inject
    DiscoverMvpPresenter<DiscoverMvpView> mPresenter;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        //noinspection ConstantConditions
        PagerAdapter adapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setClipToPadding(false);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.addOnPageChangeListener(viewPagerPageChangeListener);

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
                public void onResponse(retrofit2.Call<FeaturedSuper> call, Response<FeaturedSuper> response) {
                    //noinspection ConstantConditions
                    if(response.body().getMessage()!=null){
                        //noinspection ConstantConditions
                        mFeatureList = response.body().getMessage();
                        mFeatureRv.setVisibility(View.VISIBLE);
                        mFeatureAdapter = new FeaturedAdapter(mFeatureList, getActivity());
                        mFeatureRv.setAdapter(mFeatureAdapter);
                        mFeatureRv.setOnFlingListener(null);
                        featureSnapHelper.attachToRecyclerView(mFeatureRv);
                        mProgressFeatured.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<FeaturedSuper> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to fetch Featured Books", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void fetchCollectionNames() {
        mCollectionList.add(new Collection(R.drawable.coll1, "Best Of 2016", "ROBILLO"));
        mCollectionList.add(new Collection(R.drawable.coll2, "Best Horror Books", "ROBILLO"));
        mCollectionList.add(new Collection(R.drawable.coll3, "Best Fiction", "ROBILLO"));
        mCollectionAdapter = new CollectionsAdapter(mCollectionList, getActivity());
        mCollectionRv.setAdapter(mCollectionAdapter);
        mCollectionRv.setOnFlingListener(null);
        collectionsSnapHelper.attachToRecyclerView(mCollectionRv);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            switch (position){
                case 0:{
                    args.putInt("drawable_id", R.drawable.larry_page_quote);
                    return PagerFragment.newInstance(args);
                }
                case 1:{
                    args.putInt("drawable_id", R.drawable.larry_page);
                    return PagerFragment.newInstance(args);
                }
                case 2:{
                    args.putInt("drawable_id", R.drawable.steve_jobs_quote);
                    return PagerFragment.newInstance(args);
                }
                case 3:{
                    args.putInt("drawable_id", R.drawable.larry_page_quote);
                    return PagerFragment.newInstance(args);
                }
                default:{
                    return PagerFragment.newInstance(args);
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

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

}
