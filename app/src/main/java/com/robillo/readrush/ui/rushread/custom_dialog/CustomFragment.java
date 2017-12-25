package com.robillo.readrush.ui.rushread.custom_dialog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.di.component.ActivityComponent;
import com.robillo.readrush.ui.base.BaseFragment;
import com.robillo.readrush.ui.main.library.LibraryFragment;
import com.robillo.readrush.ui.rushread.ReadRushActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomFragment extends BaseFragment implements CustomFragmentMvpView {

    @BindView(R.id.size1)
    ImageButton mSizeOne;

    @BindView(R.id.size2)
    ImageButton mSizeTwo;

    @BindView(R.id.size3)
    ImageButton mSizeThree;

    @BindView(R.id.size4)
    ImageButton mSizeFour;

    @BindView(R.id.font1raleway)
    TextView mFont1Raleway;

    @BindView(R.id.font2tiempo)
    TextView mFont2Tiempo;

    @BindView(R.id.font3comfortaa)
    TextView mFont3Comfortaa;

    @BindView(R.id.font4georgia)
    TextView mFont4Georgia;

    @BindView(R.id.font5rounded)
    TextView mFont5Rounded;

    @BindView(R.id.outer)
    View mLayoutOuter;

    @BindView(R.id.inner_1)
    View mLayoutInner1;

    @BindView(R.id.inner_2)
    View mLayoutInner2;

    @BindView(R.id.inner_3)
    View mLayoutInner3;

    private AppPreferencesHelper mPrefsHelper;

    public CustomFragment() {
        // Required empty public constructor
    }

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_custom, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null) setUnBinder(ButterKnife.bind(this, v));

        return v;
    }

    @Override
    protected void setUp(View view) {
        //noinspection ConstantConditions
        mPrefsHelper = new AppPreferencesHelper(getActivity(), ReadRushApp.PREF_FILE_NAME);
        setInitialTheme();
    }

    @OnClick(R.id.size1)
    public void setmSizeOne() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontSize(1);
            highlightFontSize(1);
        }
    }

    @OnClick(R.id.size2)
    public void setmSizeTwo() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontSize(2);
            highlightFontSize(2);
        }
    }

    @OnClick(R.id.size3)
    public void setmSizeThree() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontSize(3);
            highlightFontSize(3);
        }
    }

    @OnClick(R.id.size4)
    public void setmSizeFour() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontSize(4);
            highlightFontSize(4);
        }
    }

    @OnClick(R.id.font1raleway)
    public void setmFont1Raleway() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontPath("font1");
        }
    }

    @OnClick(R.id.font2tiempo)
    public void setmFont2Tiempo() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontPath("font2");
        }
    }

    @OnClick(R.id.font3comfortaa)
    public void setmFont3Comfortaa() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontPath("font3");
        }
    }

    @OnClick(R.id.font4georgia)
    public void setmFont4Georgia() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontPath("font4");
        }
    }

    @OnClick(R.id.font5rounded)
    public void setmFont5Rounded() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setFontPath("font5");
        }
    }

    @OnClick(R.id.max_brightness)
    public void setmMaxBrightness() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setLightTheme();

            mLayoutOuter.setBackgroundColor(getResources().getColor(R.color.rushRed));
            mLayoutInner1.setBackgroundColor(getResources().getColor(R.color.dardRed));
            mLayoutInner2.setBackgroundColor(getResources().getColor(R.color.dardRed));
            mLayoutInner3.setBackgroundColor(getResources().getColor(R.color.dardRed));
        }
    }

    @OnClick(R.id.min_brightness)
    public void setmMinBrightness() {
        if(getActivity()!=null){
            ((ReadRushActivity) getActivity()).setDarkTheme();

            mLayoutOuter.setBackgroundColor(getResources().getColor(R.color.readBlack));
            mLayoutInner1.setBackgroundColor(getResources().getColor(R.color.black));
            mLayoutInner2.setBackgroundColor(getResources().getColor(R.color.black));
            mLayoutInner3.setBackgroundColor(getResources().getColor(R.color.black));
        }
    }

    @Override
    public void setInitialTheme() {
        switch (mPrefsHelper.getTextSize()){
            case 20:{
                highlightFontSize(1);
                break;
            }
            case 25:{
                highlightFontSize(2);
                break;
            }
            case 30:{
                highlightFontSize(3);
                break;
            }
            case 35:{
                highlightFontSize(4);
                break;
            }
        }
        switch (mPrefsHelper.getFontPath()){
            case "fonts/Raleway-Regular.ttf":{
                mFont1Raleway.setTextColor(getResources().getColor(R.color.highlight));
                mFont2Tiempo.setTextColor(getResources().getColor(R.color.white));
                mFont3Comfortaa.setTextColor(getResources().getColor(R.color.white));
                mFont4Georgia.setTextColor(getResources().getColor(R.color.white));
                mFont5Rounded.setTextColor(getResources().getColor(R.color.white));
                break;
            }
            case "fonts/CMTiempo.ttf":{
                mFont1Raleway.setTextColor(getResources().getColor(R.color.white));
                mFont2Tiempo.setTextColor(getResources().getColor(R.color.highlight));
                mFont3Comfortaa.setTextColor(getResources().getColor(R.color.white));
                mFont4Georgia.setTextColor(getResources().getColor(R.color.white));
                mFont5Rounded.setTextColor(getResources().getColor(R.color.white));
                break;
            }
            case "fonts/Comfortaa-Regular.ttf":{
                mFont1Raleway.setTextColor(getResources().getColor(R.color.white));
                mFont2Tiempo.setTextColor(getResources().getColor(R.color.white));
                mFont3Comfortaa.setTextColor(getResources().getColor(R.color.highlight));
                mFont4Georgia.setTextColor(getResources().getColor(R.color.white));
                mFont5Rounded.setTextColor(getResources().getColor(R.color.white));
                break;
            }
            case "fonts/GeorgiaBelle.ttf":{
                mFont1Raleway.setTextColor(getResources().getColor(R.color.white));
                mFont2Tiempo.setTextColor(getResources().getColor(R.color.white));
                mFont3Comfortaa.setTextColor(getResources().getColor(R.color.white));
                mFont4Georgia.setTextColor(getResources().getColor(R.color.highlight));
                mFont5Rounded.setTextColor(getResources().getColor(R.color.white));
                break;
            }
            case "fonts/Rounded_Elegance.ttf":{
                mFont1Raleway.setTextColor(getResources().getColor(R.color.white));
                mFont2Tiempo.setTextColor(getResources().getColor(R.color.white));
                mFont3Comfortaa.setTextColor(getResources().getColor(R.color.white));
                mFont4Georgia.setTextColor(getResources().getColor(R.color.white));
                mFont5Rounded.setTextColor(getResources().getColor(R.color.highlight));
                break;
            }
        }
        if((mPrefsHelper.getAppTheme()).equals("NIGHT")){
            mLayoutOuter.setBackgroundColor(getResources().getColor(R.color.readBlack));
            mLayoutInner1.setBackgroundColor(getResources().getColor(R.color.black));
            mLayoutInner2.setBackgroundColor(getResources().getColor(R.color.black));
            mLayoutInner3.setBackgroundColor(getResources().getColor(R.color.black));
        }
        else {
            mLayoutOuter.setBackgroundColor(getResources().getColor(R.color.rushRed));
            mLayoutInner1.setBackgroundColor(getResources().getColor(R.color.dardRed));
            mLayoutInner2.setBackgroundColor(getResources().getColor(R.color.dardRed));
            mLayoutInner3.setBackgroundColor(getResources().getColor(R.color.dardRed));
        }
    }

    @Override
    public void highlightFontSize(int sizeNumber) {
        switch (sizeNumber){
            case 1:{
                mSizeOne.setColorFilter(getResources().getColor(R.color.highlight));
                mSizeTwo.setColorFilter(getResources().getColor(R.color.white));
                mSizeThree.setColorFilter(getResources().getColor(R.color.white));
                mSizeFour.setColorFilter(getResources().getColor(R.color.white));
                break;
            }
            case 2:{
                mSizeOne.setColorFilter(getResources().getColor(R.color.white));
                mSizeTwo.setColorFilter(getResources().getColor(R.color.highlight));
                mSizeThree.setColorFilter(getResources().getColor(R.color.white));
                mSizeFour.setColorFilter(getResources().getColor(R.color.white));
                break;
            }
            case 3:{
                mSizeOne.setColorFilter(getResources().getColor(R.color.white));
                mSizeTwo.setColorFilter(getResources().getColor(R.color.white));
                mSizeThree.setColorFilter(getResources().getColor(R.color.highlight));
                mSizeFour.setColorFilter(getResources().getColor(R.color.white));
                break;
            }
            case 4:{
                mSizeOne.setColorFilter(getResources().getColor(R.color.white));
                mSizeTwo.setColorFilter(getResources().getColor(R.color.white));
                mSizeThree.setColorFilter(getResources().getColor(R.color.white));
                mSizeFour.setColorFilter(getResources().getColor(R.color.highlight));
                break;
            }
        }
    }

    @Override
    public void highlightText(int textCase) {
        switch (textCase){
            case 1:{
                break;
            }
            case 2:{
                break;
            }
            case 3:{
                break;
            }
            case 4:{
                break;
            }
            case 5:{
                break;
            }
        }
    }
}
