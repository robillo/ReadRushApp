package com.robillo.readrush.ui.preference;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.User;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferenceActivity extends BaseActivity implements PreferenceMvpView {

    private Boolean fromSettings;
    AppPreferencesHelper mPrefsHelper;
    PreferenceAdapter mAdapter;
    List<String> mList = new ArrayList<>();
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @Inject
    PreferenceMvpPresenter<PreferenceMvpView> mPresenter;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.done)
    Button mDoneButton;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, PreferenceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        getActivityComponent().inject(PreferenceActivity.this);

        ButterKnife.bind(this);

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        fromSettings = getIntent().getBooleanExtra("from_settings", false);
        setUpWindowAnimations();
        mList = Arrays.asList(getResources().getStringArray(R.array.preferences));
        mAdapter = new PreferenceAdapter(mList, this);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mAdapter);
    }

    @OnClick(R.id.done)
    public void onDone() {
//        startActivity(MainActivity.getStartIntent(this), mBundle);
        if(mAdapter.mSelectedItems.size()==2){
            mPrefsHelper.setUserPreference(mAdapter.mSelectedItems.get(0) + "," + mAdapter.mSelectedItems.get(1));
            Toast.makeText(this, "Please wait while we update your details.", Toast.LENGTH_SHORT).show();
            postUserDetails();
        }
        else {
            Toast.makeText(this, "You Have To Select Two Preferences", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setUpWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(400);
            getWindow().setEnterTransition(explode);
            getWindow().setReenterTransition(explode);

            Fade fade = new Fade();
            fade.setDuration(400);
            getWindow().setExitTransition(fade);

//            mBundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        }
    }

    @Override
    public void postUserDetails() {
        setApiInterface();
    }

    @Override
    public void setApiInterface() {
        //noinspection StatementWithEmptyBody
        if(fromSettings){
            //TODO call for update preferences api for user and then onBackPressed()
                Call<ResponseBody> call = mApiService.updatePreferences(mPrefsHelper.getUserId(), mPrefsHelper.getUserPreference());
        }
        else {
            Call<ResponseBody> call = mApiService.createUser(mPrefsHelper.getUserName(), mPrefsHelper.getUserEmail(), mPrefsHelper.getUserPassword(), mPrefsHelper.getUserPreference());
            if(call!=null){
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        Toast.makeText(PreferenceActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                        Log.e("response", response.message());
                        validateUser(MainActivity.getStartIntent(PreferenceActivity.this));
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Toast.makeText(PreferenceActivity.this, "Failed To Update. RETRY LATER", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    public void validateUser(final Intent intent) {
        Call<List<User>> call = mApiService.validateUser(mPrefsHelper.getUserEmail(), mPrefsHelper.getUserPassword());
        if(call!=null){
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                    Log.e("response", response.message());
                    User user = null;
                    try {
                        //noinspection ConstantConditions
                        user = response.body().get(0);
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    if(user!=null){
                        Toast.makeText(PreferenceActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
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
                        if(fromSettings){
                            onBackPressed();
                        }
                        else {
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(PreferenceActivity.this, "Incomplete User Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                    Toast.makeText(PreferenceActivity.this, "Failed To Update. RETRY LATER", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }
    }
}
