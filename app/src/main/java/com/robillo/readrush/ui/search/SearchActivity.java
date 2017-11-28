package com.robillo.readrush.ui.search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.Featured;
import com.robillo.readrush.data.network.retrofit.model.FeaturedSuper;
import com.robillo.readrush.data.network.retrofit.model.SearchResultItem;
import com.robillo.readrush.data.network.retrofit.model.SearchResultSuper;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.main.discover.adapters.FeaturedAdapter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends BaseActivity implements SearchMvpView {

    private String mSearchTags;
    FeaturedAdapter mFeatureAdapter;
    List<SearchResultItem> mFeatureList = new ArrayList<>();
    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @BindView(R.id.search)
    EditText mSearchEditText;

    @BindView(R.id.search_buttom)
    ImageView mSearchButton;

    @BindView(R.id.suggestions)
    RecyclerView mSuggestionsRv;

    @BindView(R.id.layout_suggestions)
    LinearLayout mLayoutSuggestions;

    @BindView(R.id.search_history)
    RecyclerView mSearchHistory;

    @BindView(R.id.layout_search_history)
    LinearLayout mLayoutSearchHistory;

    @Inject
    SearchMvpPresenter<SearchMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getActivityComponent().inject(SearchActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SearchActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {

        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        mSuggestionsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mLayoutSuggestions.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));

        loadDefaultFeaturedBooks();
    }

    @Override
    public void loadDefaultFeaturedBooks() {
        Call<FeaturedSuper> call = mApiService.getFeaturedBooks(mPrefsHelper.getUserId());
        if(call!=null){
            call.enqueue(new Callback<FeaturedSuper>() {
                @Override
                public void onResponse(@NonNull Call<FeaturedSuper> call, @NonNull Response<FeaturedSuper> response) {

                }

                @Override
                public void onFailure(@NonNull Call<FeaturedSuper> call, @NonNull Throwable t) {

                }
            });
        }
    }

    @Override
    public void loadSuggestions(String search_tag) {

        Call<SearchResultSuper> call = mApiService.searchResultsFetch(search_tag);
        if(call!=null){
            call.enqueue(new Callback<SearchResultSuper>() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onResponse(@NonNull Call<SearchResultSuper> call, @NonNull Response<SearchResultSuper> response) {
                    if(response.body().getMessage()!=null){
                        mFeatureList = response.body().getMessage();
                        mFeatureAdapter = new FeaturedAdapter(SearchActivity.this, mFeatureList);
                        mSuggestionsRv.setAdapter(mFeatureAdapter);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SearchResultSuper> call, @NonNull Throwable t) {

                }
            });
        }
    }

    @Override
    public void showSearchHistory() {

    }

    @OnClick(R.id.search_buttom)
    public void searchForResults(){
        if(mSearchEditText.getText().length()>0){
            loadSuggestions(mSearchEditText.getText().toString());
            mSearchEditText.setText("");
        }
        else {
            Toast.makeText(this, "Please Enter A Search Query", Toast.LENGTH_SHORT).show();
        }
    }
}
