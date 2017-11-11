package com.robillo.readrush.ui.preference;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
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

    private Bundle mBundle;
    AppPreferencesHelper mPrefsHelper;
    PreferenceAdapter mAdapter;
    List<String> mList = new ArrayList<>();
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

    @OnClick(R.id.done)
    public void onDone() {
//        startActivity(MainActivity.getStartIntent(this), mBundle);
        if(mAdapter.mSelectedItems.size()==2){
            mPrefsHelper.setUserPreference1(mAdapter.mSelectedItems.get(0));
            mPrefsHelper.setUserPreference2(mAdapter.mSelectedItems.get(1));
            Toast.makeText(this, "Please wait while we update your details.", Toast.LENGTH_SHORT).show();
            postUserDetails();
        }
        else {
            Toast.makeText(this, "You Have To Select Two Preferences", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setUp() {
        mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);
        setUpWindowAnimations();
        mList = Arrays.asList(getResources().getStringArray(R.array.preferences));
        mAdapter = new PreferenceAdapter(mList, this);
//        GridLayoutManager manager = new GridLayoutManager(this, 3);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mAdapter);
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
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = mApiService.createUser(mPrefsHelper.getUserName(), mPrefsHelper.getUserEmail(), mPrefsHelper.getUserPassword(), mPrefsHelper.getUserPreference1() + "," + mPrefsHelper.getUserPreference2());
        if(call!=null){
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(PreferenceActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    Log.e("response", response.message());
                    startActivity(MainActivity.getStartIntent(PreferenceActivity.this));
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(PreferenceActivity.this, "Failed To Update. RETRY LATER", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }
    }
}
