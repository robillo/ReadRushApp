package com.robillo.readrush.ui.preference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreferenceActivity extends BaseActivity implements PreferenceMvpView {

    PreferenceAdapter mAdapter;
    List<String> mList = new ArrayList<>();

    @Inject
    PreferenceMvpPresenter<PreferenceMvpView> mPresenter;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.done)
    Button mDoneButton;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, PreferenceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        getActivityComponent().inject(PreferenceActivity.this);

        ButterKnife.bind(this);

        mPresenter.onAttach(this);

        setUp();
    }

    @OnClick(R.id.done)
    public void onDone() {
        startActivity(MainActivity.getStartIntent(this));
    }

    @Override
    protected void setUp() {
        mList = Arrays.asList(getResources().getStringArray(R.array.preferences));
        mAdapter = new PreferenceAdapter(mList, this);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mAdapter);
    }

}