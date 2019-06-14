package com.example.tbdemo.httpcompoent;

import com.example.tbdemo.api.LoginApi;
import com.example.tbdemo.module.HttpModule;
import com.example.tbdemo.ui.login.LoginActivity;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpCompoent {
    void inject(LoginActivity loginActivity);
}
