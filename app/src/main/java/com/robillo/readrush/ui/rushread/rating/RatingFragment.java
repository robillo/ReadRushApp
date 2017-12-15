package com.robillo.readrush.ui.rushread.rating;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.library.LibraryFragment;
import com.robillo.readrush.ui.rushread.ReadRushActivity;

import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 14/12/17.
 */

public class RatingFragment extends BaseFragment {


    public RatingFragment() {
        // Required empty public constructor
    }

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rating, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){

//            component.inject(RatingFragment.this);

            setUnBinder(ButterKnife.bind(this, v));

//            mPresenter.onAttach(RatingFragment.this);

        }

        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && getActivity()!=null) ((ReadRushActivity) getActivity()).hideCustomizeLayout();
    }
}
