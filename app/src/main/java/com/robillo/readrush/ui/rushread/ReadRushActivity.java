package com.robillo.readrush.ui.rushread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.rushoverview.OverviewActivity;

/**
 * Created by robinkamboj on 09/12/17.
 */

public class ReadRushActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context, String rush_id) {
        Intent intent = new Intent(context, OverviewActivity.class);
        intent.putExtra("rush_id", rush_id);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_rush);

        setUp();
    }

    public void setUp() {

    }
}
