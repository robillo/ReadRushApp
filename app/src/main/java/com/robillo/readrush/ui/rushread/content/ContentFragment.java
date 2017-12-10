package com.robillo.readrush.ui.rushread.content;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.rushread.ReadRushActivity;

/**
 * Created by robinkamboj on 15/11/17.
 */

public class ContentFragment extends Fragment implements ContentMvpView, View.OnClickListener {

    TextView mContentTextView;
    String content;
    ScrollView mScrollContent;

    public static ContentFragment newInstance(String content) {
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_content, container, false);

        setUp(v);

        return v;
    }

    @Override
    public void setUp(View v) {
        mContentTextView = v.findViewById(R.id.content_text);
        mScrollContent = v.findViewById(R.id.scroll_content);

        mContentTextView.setOnClickListener(this);
        content = getArguments().getString("content");
        mContentTextView.setText(Html.fromHtml(content));

        refreshAttributes();
    }

    @Override
    public void refreshAttributes() {
        mContentTextView.setTextSize(getActivity().getPreferences(Context.MODE_PRIVATE).getInt("text_size", 20));
        mContentTextView.setLineSpacing(0, getActivity().getPreferences(Context.MODE_PRIVATE).getFloat("line_spacing", (float) 1.5));
        int pad = getActivity().getPreferences(Context.MODE_PRIVATE).getInt("content_padding", 10);
        mContentTextView.setPadding(pad, 10, pad, 10);
        if(getActivity().getPreferences(Context.MODE_PRIVATE).getString("theme", "day").equals("night")){
            mContentTextView.setTextColor(Color.WHITE);
            mScrollContent.setBackgroundColor(Color.BLACK);
        }
        else {
            mContentTextView.setTextColor(Color.BLACK);
            mScrollContent.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.content_text:{
                ReadRushActivity activity = (ReadRushActivity) getActivity();
                activity.hideShowCustomizeLayout();
                break;
            }
        }
    }
}
