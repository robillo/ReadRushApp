package com.robillo.readrush.ui.main.discover.PagerFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {


    public PagerFragment() {
        // Required empty public constructor
    }

    public static PagerFragment newInstance() {
        return new PagerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

}
