package com.example.tbdemo.ui.login.contract;

import com.example.tbdemo.api.LoginApi;
import com.example.tbdemo.base.BaseContract;
import com.example.tbdemo.bean.LoginBean;

public interface LoginContract {

    interface View extends BaseContract.BaseView{
        void loginSuccess(LoginBean loginBean);
    }

    interface presenter extends BaseContract.BasePresenter<View>{
        void login(String phone,String pwd);
    }
}
