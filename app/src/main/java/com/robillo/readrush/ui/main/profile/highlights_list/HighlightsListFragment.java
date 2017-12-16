package com.robillo.readrush.ui.main.profile.highlights_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robillo.readrush.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighlightsListFragment extends Fragment {


    public HighlightsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_highlights_list, container, false);
    }

}
