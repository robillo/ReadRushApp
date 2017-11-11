package com.robillo.readrush.ui.login;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ArrayRes;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.others.Conversation;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.custom.MyChatEditText;
import com.robillo.readrush.ui.custom.MyChatView;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.preference.PreferenceActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    private Bundle mBundle;
    List<Conversation> mConversations = new ArrayList<>();
    @SuppressWarnings("FieldCanBeLocal")
    private static int page = 0;
    private AppPreferencesHelper mPrefsHelper;

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.icon)
    ImageView mIcon;

    @BindView(R.id.prev)
    TextView mTextPrevious;

    @BindView(R.id.next)
    LinearLayout mLinearNext;

    @BindView(R.id.ken_current)
    TextView kenCurrent;

    @BindView(R.id.chat_primary)
    MyChatView mChatPrimary;

    @BindView(R.id.chat_secondary)
    MyChatView mChatSecondary;

    @BindView(R.id.chat_edit_text)
    MyChatEditText myChatEditText;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(LoginActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LoginActivity.this);

        setUp();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void setUp() {
        setUpWindowAnimations();
        if(page==0){
            loadConversations();
            setIsOnBoarded();
            mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
        }
        setPageDetails(page);
    }

    @Override
    public void loadConversations() {
        mConversations = mPresenter.loadLists(loadArray(R.array.ken_text_initial), loadArray(R.array.chat_edit_text_hint_initial), loadArray(R.array.chat_primary_initial), loadArray(R.array.chat_secondary_initial));
    }

    @Override
    public String[] loadArray(@ArrayRes int id) {
        return getResources().getStringArray(id);
    }

    @Override
    public void setPageDetails(int page) {
        if(page==0){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide);
            mTextPrevious.setAnimation(animation);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTextPrevious.setVisibility(View.INVISIBLE);
                }
            }, 500);
        }
        else {
            mTextPrevious.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.show);
            mTextPrevious.setAnimation(animation);
            mTextPrevious.setText(mConversations.get(page-1).getKenText());
        }

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        kenCurrent.setText(mConversations.get(page).getKenText());
        mIcon.setAnimation(animation);
        kenCurrent.setAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

        if(mConversations.get(page).getEditTextHint()!=null) {
            myChatEditText.setVisibility(View.VISIBLE);
            myChatEditText.hintText(mConversations.get(page).getEditTextHint());
            myChatEditText.setAnimation(animation);
        }
        else {
            myChatEditText.setVisibility(View.GONE);
        }

        if(mConversations.get(page).getChatPrimary()!=null) {
            mChatPrimary.setVisibility(View.VISIBLE);
            mChatPrimary.setChatText(mConversations.get(page).getChatPrimary());
            mChatPrimary.setAnimation(animation);
        }
        else {
            mChatPrimary.setVisibility(View.GONE);
        }

        if(mConversations.get(page).getChatSecondary()!=null) {
            mChatSecondary.setVisibility(View.VISIBLE);
            mChatSecondary.setChatText(mConversations.get(page).getChatSecondary());
            mChatSecondary.setAnimation(animation);
        }
        else {
            mChatSecondary.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUpWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(400);
            getWindow().setExitTransition(fade);
//            mBundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        }
    }

    @Override
    public void setIsOnBoarded() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mPrefsHelper.setUserIsOnBoarded(true);
            }
        });
    }

    @OnClick(R.id.prev)
    public void goPrev() {
        if(page!=0){
            page-=1;
            setUp();
        }
    }

//    @OnClick(R.id.next)
//    public void goNext() {
//        if(page!=mConversations.size()-1){
//            page+=1;
//            setUp();
//        }
//        else {
////            startActivity(PreferenceActivity.getStartIntent(this), mBundle);
//            startActivity(PreferenceActivity.getStartIntent(this));
//        }
//    }

    @OnClick(R.id.chat_primary)
    public void goNextCP() {
        if(myChatEditText.getText()!=null) {
            myChatEditText.nullify();
        }

        switch (page) {
            case 0:{
                page++;
                setUp();
                break;
            }
            case 1:{
                page++;
                mConversations = new ArrayList<>();
                mConversations = mPresenter.loadLists(loadArray(R.array.ken_text_login), loadArray(R.array.chat_edit_text_hint_login), loadArray(R.array.chat_primary_login), loadArray(R.array.chat_secondary_login));
                mPrefsHelper.setUserEnterMode(ReadRushApp.LOGIN_MODE);
                setUp();
                break;
            }
            case 2:{
                if(mPrefsHelper.getUserEnterMode().equals(ReadRushApp.LOGIN_MODE)){
                    page++;
                    setUp();
                }
                else {
                    goNextCS();
                }
                break;
            }
            case 3:{
                if(mPrefsHelper.getUserEnterMode().equals(ReadRushApp.LOGIN_MODE)){
                    //validate email id and password
                    //start next activity

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //startActivity(MainActivity.getStartIntent(this), mBundle);
                            startActivity(MainActivity.getStartIntent(LoginActivity.this));
                        }
                    }, 2000);
                }
                else {
                    goNextCS();
                }
                break;
            }
            default:{
                goNextCS();
                break;
            }
        }
    }

    @OnClick(R.id.chat_secondary)
    public void goNextCS() {
        if(myChatEditText.getText()!=null) {
            myChatEditText.nullify();
        }

        switch (page) {
            case 0:{
                page++;
                setUp();
                break;
            }
            case 1:{
                page++;
                mConversations = new ArrayList<>();
                mConversations = mPresenter.loadLists(loadArray(R.array.ken_text_register), loadArray(R.array.chat_edit_text_hint_register), loadArray(R.array.chat_primary_register), loadArray(R.array.chat_secondary_register));
                mPrefsHelper.setUserEnterMode(ReadRushApp.REGISTER_MODE);
                setUp();
                break;
            }
            case 2:{
                page++;
                setUp();
                break;
            }
            case 3:{
                page++;
                setUp();
                break;
            }
            case 4:{
                page++;
                setUp();
                break;
            }
            case 5:{
                page++;
                setUp();
                break;
            }
            case 6:{
                page++;
                setUp();
                break;
            }
            case 7:{
                page++;
                setUp();
                break;
            }
            case 8:{
                //add email id and password to spf
                //start preference activity

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //startActivity(PreferenceActivity.getStartIntent(this), mBundle);
                        startActivity(PreferenceActivity.getStartIntent(LoginActivity.this));
                    }
                }, 2000);
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

}
