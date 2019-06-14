package com.example.tbdemo.inter;

import android.os.Bundle;
import android.view.View;

import com.example.tbdemo.ui.login.LoginActivity;

public interface IBase {

    int getContentLayout();
    void inject();
    void initView(View view);
    void intent(Class mActivity);


    void intent(Class mActivity, Bundle bundle);


    void showLoad();
}
