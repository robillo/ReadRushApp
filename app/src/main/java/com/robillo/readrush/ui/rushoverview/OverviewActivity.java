package com.robillo.readrush.ui.rushoverview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainMvpView;
import com.robillo.readrush.ui.rushoverview.overviewFragment.OverviewFragment;
import com.robillo.readrush.ui.search.SearchActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class OverviewActivity extends BaseActivity implements OverviewMvpView {

    @Inject
    OverviewMvpPresenter<OverviewMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        getActivityComponent().inject(OverviewActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(OverviewActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        setOverviewFragment();
    }

    @Override
    public void setOverviewFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, OverviewFragment.newInstance(null)).commit();
    }
}
