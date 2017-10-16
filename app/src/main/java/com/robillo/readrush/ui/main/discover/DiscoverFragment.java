package com.robillo.readrush.ui.main.discover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.library.LibraryFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends BaseFragment implements DiscoverMvpView {

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

    }
}
