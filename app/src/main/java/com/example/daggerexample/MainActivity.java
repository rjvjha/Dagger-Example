package com.example.daggerexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.daggerexample.data.DataManager;
import com.example.daggerexample.data.model.User;
import com.example.daggerexample.di.component.ActivityComponent;
import com.example.daggerexample.di.component.DaggerActivityComponent;
import com.example.daggerexample.di.module.ActivityModule;

import javax.inject.Inject;


/* A demo android app that implements Dependency Injection using Dagger2*/

public class MainActivity extends AppCompatActivity {


    @Inject
    DataManager mDataManager;

    private ActivityComponent activityComponent;

    private TextView mTvUserInfo;
    private TextView mTvAccessToken;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(DemoApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mTvUserInfo = findViewById(R.id.tv_user_info);
        mTvAccessToken = findViewById(R.id.tv_access_token);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createUser();
        getUser();
        mDataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543");

        String token = mDataManager.getAccessToken();
        if (token != null) {
            mTvAccessToken.setText(token);
        }
    }

    private void createUser() {
        try {
            mDataManager.createUser(new User("Ali", "1367, Gurgaon, Haryana, India"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUser() {
        try {
            User user = mDataManager.getUser(1L);
            mTvUserInfo.setText(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
