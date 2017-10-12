package com.robillo.readrush.ui.login.fragment;


import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.login.*;
import com.robillo.readrush.ui.onboard.fragment.OnboardFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements LoginMvpView {


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        setUnBinder(ButterKnife.bind(this, v));

        setUp(v);

        return v;
    }

    @Override
    protected void setUp(View view) {

    }
}
