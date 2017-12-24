package com.robillo.readrush.ui.done_activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.rushread.ReadRushActivity;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoneActivity extends BaseActivity implements RatingDialogListener {

    @SuppressWarnings("FieldCanBeLocal")
    private static String mRushName = null;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.done_and_dusted)
    TextView mDoneAndDusted;

    @BindView(R.id.rate_review_app)
    TextView mRateReviewApp;

    @BindView(R.id.back)
    ImageButton mBack;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.dardDarkRed));
        }

        mRushName = getIntent().getStringExtra("rush_name");
        mName.setText(mRushName);

        showDialog();
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
                .setTitle("Rate this application")
                .setDescription("Please select some stars and give your feedback")
                .setDefaultComment("This book is..")
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

    @Override
    public void onPositiveButtonClicked(int i, String s) {

    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}
