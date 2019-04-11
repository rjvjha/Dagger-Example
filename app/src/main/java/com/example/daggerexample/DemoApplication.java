package com.example.daggerexample;

import android.app.Application;
import android.content.Context;

import com.example.daggerexample.data.DataManager;
import com.example.daggerexample.di.component.ApplicationComponent;
import com.example.daggerexample.di.component.DaggerApplicationComponent;
import com.example.daggerexample.di.module.ApplicationModule;

import javax.inject.Inject;


/*This class gets DataManager through DI.*/

public class DemoApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;


    public static DemoApplication get(Context context) {
        return (DemoApplication) context.getApplicationContext();

    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
