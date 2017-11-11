package com.robillo.readrush.ui.main.discover.PagerFragment;


import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends BaseFragment {

    int mDrawableId;

    @BindView(R.id.pager_image)
    ImageView mPagerImage;

    public PagerFragment() {
        // Required empty public constructor
    }

    public static PagerFragment newInstance(Bundle bundle) {
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.bind(this, v);
        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        Glide.with(getActivity()).load(getArguments().getInt("drawable_id")).centerCrop().crossFade().into(mPagerImage);
    }
}