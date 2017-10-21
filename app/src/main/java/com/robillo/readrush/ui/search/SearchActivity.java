package com.robillo.readrush.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchMvpView {

    @Inject
    SearchMvpPresenter<SearchMvpView> mPresenter;

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

    }
}
