package com.iherrera.androidmvp.login;

import com.iherrera.androidmvp.login.model.User;

public interface LoginRepository {
    void saveUser(User user);

    User getUser();
}
