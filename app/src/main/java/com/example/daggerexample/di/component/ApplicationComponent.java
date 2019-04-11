package com.example.daggerexample.di.component;


import android.app.Application;
import android.content.Context;

import com.example.daggerexample.DemoApplication;
import com.example.daggerexample.data.DataManager;
import com.example.daggerexample.data.DbHelper;
import com.example.daggerexample.data.SharedPrefsHelper;
import com.example.daggerexample.di.ApplicationContext;
import com.example.daggerexample.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/* ApplicationComponent is an interface that is implemented by Dagger. Using @Component we specify the class to be a Component.*/

/**
 * Here we have written a method inject where we pass the DemoApplication instance. Why do we do it?
 * When the dependencies are provided through field injection i.e.
 *
 * @inject on the member variables, we have to tell the Dagger to scan this class through the implementation of this interface.
 * This class also provides methods that are used to access the dependencies that exist in the dependency graph.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DemoApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DbHelper getDbHelper();


}
