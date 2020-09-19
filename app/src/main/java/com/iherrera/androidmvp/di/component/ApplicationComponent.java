package com.iherrera.androidmvp.di.component;

import com.iherrera.androidmvp.di.module.ApplicationModule;
import com.iherrera.androidmvp.http.ApiModule;
import com.iherrera.androidmvp.login.LoginModule;
import com.iherrera.androidmvp.login.view.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity target);
}