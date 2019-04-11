package com.example.daggerexample.di.component;

import com.example.daggerexample.MainActivity;
import com.example.daggerexample.di.PerActivity;
import com.example.daggerexample.di.module.ActivityModule;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)

public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}

