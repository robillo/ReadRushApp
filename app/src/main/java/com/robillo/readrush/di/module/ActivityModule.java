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
import com.robillo.readrush.ui.main.MainMvpPresenter;
import com.robillo.readrush.ui.main.MainMvpView;
import com.robillo.readrush.ui.main.MainPresenter;
import com.robillo.readrush.ui.main.discover.DiscoverMvpPresenter;
import com.robillo.readrush.ui.main.discover.DiscoverMvpView;
import com.robillo.readrush.ui.main.discover.DiscoverPresenter;
import com.robillo.readrush.ui.main.library.LibraryMvpPresenter;
import com.robillo.readrush.ui.main.library.LibraryMvpView;
import com.robillo.readrush.ui.main.library.LibraryPresenter;
import com.robillo.readrush.ui.main.profile.ProfileMvpPresenter;
import com.robillo.readrush.ui.main.profile.ProfileMvpView;
import com.robillo.readrush.ui.main.profile.ProfilePresenter;
import com.robillo.readrush.ui.preference.PreferenceMvpPresenter;
import com.robillo.readrush.ui.preference.PreferenceMvpView;
import com.robillo.readrush.ui.preference.PreferencePresenter;
import com.robillo.readrush.ui.login.LoginMvpPresenter;
import com.robillo.readrush.ui.login.LoginMvpView;
import com.robillo.readrush.ui.login.LoginPresenter;
import com.robillo.readrush.ui.onboard.OnboardMvpPresenter;
import com.robillo.readrush.ui.onboard.OnboardMvpView;
import com.robillo.readrush.ui.onboard.OnboardPresenter;
import com.robillo.readrush.ui.onboard.fragment.OnboardFMvpPresenter;
import com.robillo.readrush.ui.onboard.fragment.OnboardFMvpView;
import com.robillo.readrush.ui.onboard.fragment.OnboardFPresenter;
import com.robillo.readrush.ui.rushoverview.OverviewMvpPresenter;
import com.robillo.readrush.ui.rushoverview.OverviewMvpView;
import com.robillo.readrush.ui.rushoverview.OverviewPresenter;
import com.robillo.readrush.ui.rushoverview.overviewFragment.OverviewFragmentMvpPresenter;
import com.robillo.readrush.ui.rushoverview.overviewFragment.OverviewFragmentMvpView;
import com.robillo.readrush.ui.rushoverview.overviewFragment.OverviewFragmentPresenter;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsMvpPresenter;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsMvpView;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsPresenter;
import com.robillo.readrush.ui.rushread.ReadRushMvpPresenter;
import com.robillo.readrush.ui.rushread.ReadRushMvpView;
import com.robillo.readrush.ui.rushread.ReadRushPresenter;
import com.robillo.readrush.ui.search.SearchMvpPresenter;
import com.robillo.readrush.ui.search.SearchMvpView;
import com.robillo.readrush.ui.search.SearchPresenter;
import com.robillo.readrush.ui.splash.SplashMvpPresenter;
import com.robillo.readrush.ui.splash.SplashMvpView;
import com.robillo.readrush.ui.splash.SplashPresenter;
import com.robillo.readrush.utils.rx.AppSchedulerProvider;
import com.robillo.readrush.utils.rx.SchedulerProvider;

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
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SearchMvpPresenter<SearchMvpView> provideSearchPresenter(
            SearchPresenter<SearchMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OverviewMvpPresenter<OverviewMvpView> provideOverviewPresenter(
            OverviewPresenter<OverviewMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ReadRushMvpPresenter<ReadRushMvpView> provideReadRushPresenter(
            ReadRushPresenter<ReadRushMvpView> presenter) {
        return presenter;
    }

    @Provides
    OverviewFragmentMvpPresenter<OverviewFragmentMvpView> provideOverviewFragmentPresenter(
            OverviewFragmentPresenter<OverviewFragmentMvpView> presenter) {
        return presenter;
    }

    @Provides
    ReviewsMvpPresenter<ReviewsMvpView> provideReviewsPresenter(
            ReviewsPresenter<ReviewsMvpView> presenter) {
        return presenter;
    }

    @Provides
    PreferenceMvpPresenter<PreferenceMvpView> providePreferencePresenter(
            PreferencePresenter<PreferenceMvpView> presenter) {
        return presenter;
    }

    @Provides
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    OnboardFMvpPresenter<OnboardFMvpView> provideOnboardFPresenter(
            OnboardFPresenter<OnboardFMvpView> presenter) {
        return presenter;
    }

    @Provides
    LibraryMvpPresenter<LibraryMvpView> provideLibraryPresenter(
            LibraryPresenter<LibraryMvpView> presenter) {
        return presenter;
    }

    @Provides
    DiscoverMvpPresenter<DiscoverMvpView> provideDiscoverPresenter(
            DiscoverPresenter<DiscoverMvpView> presenter) {
        return presenter;
    }

    @Provides
    ProfileMvpPresenter<ProfileMvpView> provideProfilePresenter(
            ProfilePresenter<ProfileMvpView> presenter) {
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
