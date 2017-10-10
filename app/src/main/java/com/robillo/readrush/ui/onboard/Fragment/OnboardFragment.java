package com.robillo.readrush.ui.onboard.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardFragment extends BaseFragment {


    public OnboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_onboard, container, false);
        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {

    }
}
