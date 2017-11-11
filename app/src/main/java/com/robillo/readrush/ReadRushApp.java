package com.robillo.readrush;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.di.component.ApplicationComponent;
import com.robillo.readrush.di.component.DaggerApplicationComponent;
import com.robillo.readrush.di.module.ApplicationModule;
import com.robillo.readrush.utils.AppLogger;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by robinkamboj on 10/10/17.
 */

public class ReadRushApp extends Application{

    @Inject
    public DataManager mDataManager;

    public static final String PREF_FILE_NAME = "READRUSH";
    public static String LOGIN_MODE = "LOGIN";
    public static String REGISTER_MODE = "REGISTER";

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
