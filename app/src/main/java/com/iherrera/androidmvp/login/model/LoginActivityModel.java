package com.iherrera.androidmvp.login.model;

import com.iherrera.androidmvp.login.LoginActivityMVP;
import com.iherrera.androidmvp.login.LoginRepository;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        //lógica de business
        repository.saveUser(new User(firstName, lastName));
    }

    @Override
    public User getUser() {
        //lógica de business
        return repository.getUser();
    }
}