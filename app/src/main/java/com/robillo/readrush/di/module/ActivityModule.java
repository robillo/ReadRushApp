/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.robillo.readrush.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.robillo.readrush.di.ActivityContext;
import com.robillo.readrush.di.PerActivity;
import com.robillo.readrush.ui.interests.PreferenceMvpPresenter;
import com.robillo.readrush.ui.interests.PreferenceMvpView;
import com.robillo.readrush.ui.interests.PreferencePresenter;
import com.robillo.readrush.ui.login.LoginMvpPresenter;
import com.robillo.readrush.ui.login.LoginMvpView;
import com.robillo.readrush.ui.login.LoginPresenter;
import com.robillo.readrush.ui.onboard.OnboardMvpPresenter;
import com.robillo.readrush.ui.onboard.OnboardMvpView;
import com.robillo.readrush.ui.onboard.OnboardPresenter;
import com.robillo.readrush.ui.onboard.fragment.OnboardFMvpPresenter;
import com.robillo.readrush.ui.onboard.fragment.OnboardFMvpView;
import com.robillo.readrush.ui.onboard.fragment.OnboardFPresenter;
import com.robillo.readrush.ui.splash.SplashMvpPresenter;
import com.robillo.readrush.ui.splash.SplashMvpView;
import com.robillo.readrush.ui.splash.SplashPresenter;
import com.robillo.readrush.utils.rx.AppSchedulerProvider;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OnboardMvpPresenter<OnboardMvpView> provideOnboardPresenter(
            OnboardPresenter<OnboardMvpView> presenter) {
        return presenter;
    }

    @Provides
    OnboardFMvpPresenter<OnboardFMvpView> provideOnboardFPresenter(
            OnboardFPresenter<OnboardFMvpView> presenter) {
        return presenter;
    }

    @Provides
    PreferenceMvpPresenter<PreferenceMvpView> providePreferencePresenter(
            PreferencePresenter<PreferenceMvpView> presenter) {
        return presenter;
    }

//    @Provides
//    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
//            AboutPresenter<AboutMvpView> presenter) {
//        return presenter;
//    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

//    @Provides
//    @PerActivity
//    MainMvpPresenter<MainMvpView> provideMainPresenter(
//            MainPresenter<MainMvpView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
//            RatingDialogPresenter<RatingDialogMvpView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
//            FeedPresenter<FeedMvpView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
//            OpenSourcePresenter<OpenSourceMvpView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
//            BlogPresenter<BlogMvpView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    PagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
//        return new FeedPagerAdapter(activity.getSupportFragmentManager());
//    }
//
//    @Provides
//    OpenSourceAdapter provideOpenSourceAdapter() {
//        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
//    }
//
//    @Provides
//    BlogAdapter provideBlogAdapter() {
//        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
//    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
