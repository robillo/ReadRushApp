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

package com.robillo.readrush.di.component;

import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.main.discover.DiscoverFragment;
import com.robillo.readrush.ui.main.library.LibraryFragment;
import com.robillo.readrush.ui.main.profile.ProfileFragment;
import com.robillo.readrush.ui.preference.PreferenceActivity;
import com.robillo.readrush.ui.login.LoginActivity;
import com.robillo.readrush.ui.onboard.OnboardActivity;
import com.robillo.readrush.ui.onboard.fragment.OnboardFragment;
import com.robillo.readrush.ui.rushoverview.OverviewActivity;
import com.robillo.readrush.ui.rushoverview.overviewFragment.OverviewFragment;
import com.robillo.readrush.ui.rushoverview.reviewsFragment.ReviewsFragment;
import com.robillo.readrush.ui.rushread.ReadRushActivity;
import com.robillo.readrush.ui.search.SearchActivity;
import com.robillo.readrush.ui.settings.SettingsActivity;
import com.robillo.readrush.ui.splash.SplashActivity;
import com.robillo.readrush.di.PerActivity;
import com.robillo.readrush.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by janisharali on 27/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(OnboardActivity activity);

    void inject(LoginActivity activity);

    void inject(PreferenceActivity activity);

    void inject(MainActivity activity);

    void inject(SearchActivity activity);

    void inject(OverviewActivity activity);

    void inject(ReadRushActivity activity);

    void inject(SettingsActivity activity);

    void inject(OnboardFragment fragment);

    void inject(LibraryFragment fragment);

    void inject(DiscoverFragment fragment);

    void inject(ProfileFragment fragment);

    void inject(OverviewFragment fragment);

    void inject(ReviewsFragment fragment);

}
