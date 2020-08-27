package com.iherrera.androidmvp.login;

import com.iherrera.androidmvp.login.model.LoginActivityModel;
import com.iherrera.androidmvp.login.presenter.LoginActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model) {
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository) {
        return new LoginActivityModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository() {
        return new MemoryRepository();
    }
}
