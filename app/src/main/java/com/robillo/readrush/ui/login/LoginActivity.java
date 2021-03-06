package com.robillo.readrush.ui.login;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.User;
import com.robillo.readrush.data.others.Conversation;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.custom.MyChatEditText;
import com.robillo.readrush.ui.custom.MyChatView;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.preference.PreferenceActivity;
import com.robillo.readrush.utils.CommonUtils;
import com.robillo.readrush.utils.KeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    private Bundle mBundle;
    List<Conversation> mConversations = new ArrayList<>();
    @SuppressWarnings("FieldCanBeLocal")
    private static int page = 0;
    private AppPreferencesHelper mPrefsHelper;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

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

        page = 0;

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
            mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
            loadConversations();
            setIsOnBoarded();
        }
        setPageDetails(page);
    }

    @Override
    public void loadConversations() {
        mConversations = loadLists(loadArray(R.array.ken_text_initial), loadArray(R.array.chat_edit_text_hint_initial), loadArray(R.array.chat_primary_initial), loadArray(R.array.chat_secondary_initial));
    }

    public List<Conversation> loadLists(String[] ken, String[] hint, String[] primary, String[] secondary) {
        List<Conversation> mList = new ArrayList<>();
        for(int i=0; i<ken.length; i++){
            mList.add(new Conversation(ken[i], hint[i], primary[i], secondary[i]));
        }
        return mList;
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
        mPrefsHelper.setUserIsOnBoarded(true);
    }

    @Override
    public void nullifyMyChatEditText() {
        if(myChatEditText.getText()!=null) {
            myChatEditText.nullify();
        }
    }

    @Override
    public void validateUser(final Intent intent) {
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<User>> call = mApiService.validateUser(mPrefsHelper.getUserEmail(), mPrefsHelper.getUserPassword());
        if(call!=null){
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                    User user = null;
                    try {
                        //noinspection ConstantConditions
                        user = response.body().get(0);
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                    if(user!=null){
                        Toast.makeText(LoginActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                        mPrefsHelper.setUserId(user.getUser_id());
                        mPrefsHelper.setUserName(user.getName());
                        //noinspection ConstantConditions
                        mPrefsHelper.setUserEmail(user.getEmail_id());
                        mPrefsHelper.setUserPassword(user.getPassword());
                        mPrefsHelper.setDateTime(user.getDatetime());
                        mPrefsHelper.setDisplayPicture(user.getDisplay_picture());
                        mPrefsHelper.setFacebookId(user.getFacebook_id());
                        mPrefsHelper.setGoogleId(user.getGoogle_id());
                        mPrefsHelper.setLibrary(user.getLibrary());
                        mPrefsHelper.setUserPreference(user.getPreference());
                        mPrefsHelper.setPreferenceCode(user.getPreference_code());
                        mPrefsHelper.setRead(user.getRead());
                        mPrefsHelper.setRushCount(user.getRush_count());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                    Toast.makeText(LoginActivity.this, "Network Error. Please try again.", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }
    }

    @OnClick(R.id.prev)
    public void goPrev() {

        hideKeyboard();

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

        hideKeyboard();

        switch (page) {
            case 0:{ // I AM KEN
                page++;
                setUp();
                break;
            }
            case 1:{ // MET BEFORE
                page++;
                mConversations = new ArrayList<>();
                mConversations = mPresenter.loadLists(loadArray(R.array.ken_text_login), loadArray(R.array.chat_edit_text_hint_login), loadArray(R.array.chat_primary_login), loadArray(R.array.chat_secondary_login));
                mPrefsHelper.setUserEnterMode(ReadRushApp.LOGIN_MODE);
                setUp();
                break;
            }
            case 2:{ // ENTER EMAIL ID => VALIDATE EMAIL ID

                if(mPrefsHelper.getUserEnterMode().equals(ReadRushApp.LOGIN_MODE)){

                    //validate email
                    boolean isEmailValid = CommonUtils.isEmailValid(myChatEditText.getText());
                    if(isEmailValid){
                        //save email in prefs
                        mPrefsHelper.setUserEmail(myChatEditText.getText());
                        nullifyMyChatEditText();
                        page++;
                        setUp();
                    }
                    else {
                        Toast.makeText(this, "Please Enter a Valid Email address", Toast.LENGTH_SHORT).show();;
                    }
                }
                else {
                    goNextCS();
                }
                break;
            }
            case 3:{ // ENTER PASSWORD => VALIDATE PASSWORD

                if(mPrefsHelper.getUserEnterMode().equals(ReadRushApp.LOGIN_MODE)){

                    //validate password
//                    boolean isPasswordValid = CommonUtils.isValidPassword(myChatEditText.getText());
                    if(myChatEditText.getText().length()>8){
                        //save password in prefs
                        mPrefsHelper.setUserPassword(myChatEditText.getText());
                        Toast.makeText(this, "Please Wait While We Log You In.", Toast.LENGTH_SHORT).show();

                        validateUser(MainActivity.getStartIntent(LoginActivity.this));
                    }
                    else {
                        Toast.makeText(this, "Please Enter a Valid Password", Toast.LENGTH_SHORT).show();;
                    }

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

        hideKeyboard();

        switch (page) {
            case 0:{ // I AM KEN
                page++;
                setUp();
                break;
            }
            case 1:{ // MET BEFORE
                page++;
                mConversations = new ArrayList<>();
                mConversations = mPresenter.loadLists(loadArray(R.array.ken_text_register), loadArray(R.array.chat_edit_text_hint_register), loadArray(R.array.chat_primary_register), loadArray(R.array.chat_secondary_register));
                mPrefsHelper.setUserEnterMode(ReadRushApp.REGISTER_MODE);
                setUp();
                break;
            }
            case 2:{ // PLEASED TO MEET YOU
                page++;
                setUp();
                break;
            }
            case 3:{ // CHANGE ANSWER
                page++;
                setUp();
                break;
            }
            case 4:{ // ENTER EMAIL ID => VALIDATE EMAIL ID

                boolean isEmailValid = CommonUtils.isEmailValid(myChatEditText.getText());
                if(isEmailValid){
                    //save email in prefs
                    mPrefsHelper.setUserEmail(myChatEditText.getText());
                    nullifyMyChatEditText();
                    page++;
                    setUp();
                }
                else {
                    Toast.makeText(this, "Please Enter a Valid Email ID", Toast.LENGTH_SHORT).show();;
                }
                break;
            }
            case 5:{ // ENTER PASSWORD => VALIDATE PASSWORD

//                boolean isPasswordValid = CommonUtils.isValidPassword(myChatEditText.getText());
                if(myChatEditText.getText().length()>8 && myChatEditText.getText().matches(".*\\d+.*")){
                    //save password in prefs
                    mPrefsHelper.setUserPassword(myChatEditText.getText());
                    nullifyMyChatEditText();
                    page++;
                    setUp();
                }
                else {
                    Toast.makeText(this, "Please enter a password with more than 8 characters and atleast one number", Toast.LENGTH_SHORT).show();;
                }
                break;
            }
            case 6:{ //ASK QUESTIONS
                page++;
                setUp();
                break;
            }
            case 7:{ // WHATS YOUR NAME  => VALIDATE USER NAME

//                boolean isValidUserName = CommonUtils.isValidUserName(myChatEditText.getText());
                if(myChatEditText.getText().length()>5){
                    //save username in prefs
                    mPrefsHelper.setUserName(myChatEditText.getText());
                    nullifyMyChatEditText();
                    page++;
                    setUp();
                }
                else {
                    Toast.makeText(this, "Please Enter a User name with at least 5 characters", Toast.LENGTH_SHORT).show();;
                }
                nullifyMyChatEditText();

                break;
            }
            case 8:{ // CHOOSE PREFERENCES
                //add email id and password to spf
                //start preference activity

                //startActivity(PreferenceActivity.getStartIntent(this), mBundle);
                startActivity(PreferenceActivity.getStartIntent(LoginActivity.this));
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
