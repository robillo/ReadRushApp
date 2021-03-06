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

import android.app.Application;
import android.content.Context;

import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.data.db.RoomAppDatabase;
import com.robillo.readrush.data.db.model.library.LibraryContentRepository;
import com.robillo.readrush.data.db.model.library.LibraryCoverContent;
import com.robillo.readrush.data.db.model.library.LibraryCoverDao;
import com.robillo.readrush.data.db.model.library.LibraryCoverRepository;
import com.robillo.readrush.data.db.model.search.SearchNameDao;
import com.robillo.readrush.data.db.model.search.SearchNameRepository;
import com.robillo.readrush.di.ApplicationContext;
import com.robillo.readrush.di.module.ApplicationModule;
import com.robillo.readrush.di.module.RoomModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
@Component(dependencies = {}, modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(ReadRushApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

    SearchNameDao searchNameDao();

    LibraryCoverDao libraryContentDao();

    RoomAppDatabase roomAppDatabase();

    SearchNameRepository searchNameRepository();

    LibraryCoverRepository libraryCoverRepository();

    LibraryContentRepository libraryContentRepository();
}