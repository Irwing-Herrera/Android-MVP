package com.iherrera.androidmvp;

import android.app.Application;

import com.iherrera.androidmvp.di.component.ApplicationComponent;
import com.iherrera.androidmvp.di.component.DaggerApplicationComponent;
import com.iherrera.androidmvp.di.module.ApplicationModule;
import com.iherrera.androidmvp.login.LoginModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}