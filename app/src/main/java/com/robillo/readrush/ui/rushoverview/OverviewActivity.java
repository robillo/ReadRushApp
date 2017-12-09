package com.robillo.readrush.ui.rushoverview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.main.MainMvpView;
import com.robillo.readrush.ui.rushoverview.overviewFragment.OverviewFragment;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsFragment;
import com.robillo.readrush.ui.search.SearchActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OverviewActivity extends BaseActivity implements OverviewMvpView {

    @SuppressWarnings("FieldCanBeLocal")
    private static String mRushId;

    @Inject
    OverviewMvpPresenter<OverviewMvpView> mPresenter;

    public static Intent getStartIntent(Context context, String rush_id) {
        Intent intent = new Intent(context, OverviewActivity.class);
        intent.putExtra("rush_id", rush_id);
        return intent;
    }

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
        mRushId = getIntent().getStringExtra("rush_id");
        setOverviewFragment(mRushId);
    }

    @Override
    public void setOverviewFragment(String rushId) {
        Bundle args = new Bundle();
        args.putString("rush_id", rushId);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, OverviewFragment.newInstance(args)).commit();
    }
}
