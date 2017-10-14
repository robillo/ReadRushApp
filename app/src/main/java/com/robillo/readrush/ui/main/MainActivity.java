package com.robillo.readrush.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.onboard.OnboardActivity;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

import static com.robillo.readrush.R.array.colors;

public class MainActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_book_black_24dp),
                        getResources().getColor(R.color.white)
                ).title("Heart")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_whatshot_black_24dp),
                        getResources().getColor(R.color.white)
                ).title("Cup")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_person_black_24dp),
                        getResources().getColor(R.color.white)
                ).title("Diploma")
                        .build()
        );
        navigationTabBar.setModels(models);
    }

}
