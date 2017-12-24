package com.robillo.readrush.ui.rushread.content;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.rushread.ReadRushActivity;
import com.robillo.readrush.utils.ViewUtils;

/**
 * Created by robinkamboj on 15/11/17.
 */

public class ContentFragment extends Fragment implements ContentMvpView {

    TextView mContentTextView;
    String content;
    ScrollView mScrollContent;
    @SuppressWarnings("FieldCanBeLocal")
    private AppPreferencesHelper mPrefsHelper;

    public static ContentFragment newInstance(String content) {
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(getActivity(), ReadRushApp.PREF_FILE_NAME);

//        mContentTextView.setOnClickListener(this);

        //noinspection ConstantConditions
        content = getArguments().getString("content");

        Log.e("content", Html.fromHtml(content).toString());
        mContentTextView.setText(Html.fromHtml(content));

        refreshAttributes();
    }

    @Override
    public void refreshAttributes() {
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mPrefsHelper.getTextSize());
        mContentTextView.setLineSpacing(0, mPrefsHelper.getLineSpacing());
        Typeface typeface = null;
        if(getActivity()!=null) typeface = Typeface.createFromAsset(getActivity().getAssets(), mPrefsHelper.getFontPath());
        mContentTextView.setTypeface(typeface);
        int pad = mPrefsHelper.getContentPadding();
        mContentTextView.setPadding(pad, 10, pad, 10);
        if((mPrefsHelper.getAppTheme()).equals("NIGHT")){
            mContentTextView.setTextColor(Color.WHITE);
            mScrollContent.setBackgroundColor(Color.BLACK);
        }
        else {
            mContentTextView.setTextColor(Color.BLACK);
            mScrollContent.setBackgroundColor(Color.WHITE);
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.content_text:{
//                ReadRushActivity activity = (ReadRushActivity) getActivity();
//                if (activity != null) {
//                    activity.hideShowCustomizeLayout();
//                }
//                break;
//            }
//        }
//    }
}
