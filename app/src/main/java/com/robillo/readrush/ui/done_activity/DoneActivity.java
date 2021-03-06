package com.robillo.readrush.ui.done_activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.Featured;
import com.robillo.readrush.data.network.retrofit.model.FeaturedSuper;
import com.robillo.readrush.data.prefs.AppPreferencesHelper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.main.discover.adapters.FeaturedAdapter;
import com.robillo.readrush.ui.rushread.ReadRushActivity;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoneActivity extends BaseActivity implements DoneMvpView, RatingDialogListener {

    @SuppressWarnings("FieldCanBeLocal")
    private static String mRushName = null, mRushId = null;
    List<Featured> mFeatureList = new ArrayList<>();
    FeaturedAdapter mFeatureAdapter;

    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;
    private AppPreferencesHelper mPrefsHelper;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.done_and_dusted)
    TextView mDoneAndDusted;

    @BindView(R.id.rate_review_app)
    TextView mRateReviewApp;

    @BindView(R.id.back)
    ImageButton mBack;

    @BindView(R.id.try_these_next)
    RecyclerView mRecyclerTryNext;

    @BindView(R.id.progress_bar_try_these_next)
    ProgressBar mProgressTryNext;

    public static Intent getStartIntent(Context context, String rush_id, String rush_name) {
        Intent intent = new Intent(context, DoneActivity.class);
        intent.putExtra("rush_id", rush_id);
        intent.putExtra("rush_name", rush_name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        setUnBinder(ButterKnife.bind(this));

        setUp();
    }

    @Override
    protected void setUp() {

        mRushName = getIntent().getStringExtra("rush_name");
        mRushId = getIntent().getStringExtra("rush_id");

        mApiService = ApiClient.getClient().create(ApiInterface.class);
        mPrefsHelper = new AppPreferencesHelper(this, ReadRushApp.PREF_FILE_NAME);

        fetchFeaturedBooks();

        //SETTING TRY NEXT RV
        mRecyclerTryNext.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.dardDarkRed));
        }

        mName.setText(mRushName);

        //check whether has reviewed book for resp. rush_id and accordingly change "rate and review rush" and "update rush review"
    }

    @OnClick(R.id.back)
    public void setmBack() {
        onBackPressed();
    }

    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
                .setTitle("Rate This Rush:")
                .setDescription("Please select some stars and give your feedback")
                .setDefaultComment("This rush is..")
                .setStarColor(R.color.dardRed)
                .setNoteDescriptionTextColor(R.color.colorTextFour)
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimaryDark)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.white)
                .setCommentTextColor(R.color.white)
                .setCommentBackgroundColor(R.color.dardReadBlack)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(DoneActivity.this)
                .show();
    }

    @OnClick(R.id.rate_review_app)
    public void setmRateReviewApp() {
        showDialog();
    }

    @Override
    public void fetchFeaturedBooks() {
        retrofit2.Call<FeaturedSuper> call = mApiService.getFeaturedBooks(mPrefsHelper.getUserId());
        if(call!=null){
            call.enqueue(new Callback<FeaturedSuper>() {
                @Override
                public void onResponse(@NonNull retrofit2.Call<FeaturedSuper> call, @NonNull Response<FeaturedSuper> response) {
                    //noinspection ConstantConditions
                    if(response.body().getMessage()!=null){
                        //noinspection ConstantConditions
                        mFeatureList = response.body().getMessage();
                        //noinspection ConstantConditions
                        if(mRecyclerTryNext!=null){
                            mRecyclerTryNext.setVisibility(View.VISIBLE);
                            mFeatureAdapter = new FeaturedAdapter(mFeatureList, DoneActivity.this);
                            mRecyclerTryNext.setAdapter(mFeatureAdapter);
                            mRecyclerTryNext.setOnFlingListener(null);
                        }
                        //noinspection ConstantConditions
                        if(mProgressTryNext!=null) mProgressTryNext.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(@NonNull retrofit2.Call<FeaturedSuper> call, @NonNull Throwable t) {
                    //noinspection ConstantConditions
                    if(DoneActivity.this!=null){
                        Toast.makeText(DoneActivity.this, "Failed to fetch Featured Books", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onPositiveButtonClicked(int rating, String review) {
        //Call to review rush

        Call<ResponseBody> call = mApiService.createReview(mPrefsHelper.getUserId(), mRushId, String.valueOf(rating), review);
        if(call!=null){
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    Log.e("response", " " + response.toString() + response.message() + response.isSuccessful());
                    Toast.makeText(DoneActivity.this, "Review posted successfully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(DoneActivity.this, "Your review can now be viewed in rush detail screen", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Toast.makeText(DoneActivity.this, "Failed to post review", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onNegativeButtonClicked() {
        //Auto - dismiss ; do nothing
    }

    @Override
    public void onNeutralButtonClicked() {
        //Auto - dismiss ; do nothing
    }

    @Override
    public void onBackPressed() {
        startActivity(MainActivity.getStartIntent(this));
    }
}
