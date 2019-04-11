package com.example.daggerexample.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.daggerexample.di.ApplicationContext;
import com.example.daggerexample.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;


/* We have to provide the dependencies expressed in the DemoApplicationclass.
 This class needs DataManager and to provide this class we have to provide the dependencies expressed by DataManager,
  which as mentioned in the constructor are Context, DbHelper, and SharedPrefsHelper . We then move further in the graph.

   1. Context has to be ApplicationContext
   2. DbHelper needs Context, dbName, and version. This does not have any further branching.
   3. SharedPrefsHelper needs SharedPreferences

    We now accumulate the superset of all these dependencies, which turn out to be:
    Context, dbName, version, and SharedPreferences

 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        this.mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }

}
