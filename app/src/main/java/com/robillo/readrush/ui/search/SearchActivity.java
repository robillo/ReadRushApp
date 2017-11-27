package com.robillo.readrush.ui.search;

import android.content.Context;
import android.content.Intent;
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

import com.robillo.readrush.R;
import com.robillo.readrush.data.network.retrofit.model.Featured;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.main.discover.adapters.FeaturedAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchMvpView {

    FeaturedAdapter mFeatureAdapter;
    List<Featured> mFeatureList = new ArrayList<>();

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
        showSuggestions();
    }

    @Override
    public void showSuggestions() {
        mSuggestionsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mFeatureAdapter = new FeaturedAdapter(mFeatureList, this);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        mSuggestionsRv.setAdapter(mFeatureAdapter);
        mLayoutSuggestions.setAnimation(animation);
    }

    @Override
    public void showSearchHistory() {

    }
}
