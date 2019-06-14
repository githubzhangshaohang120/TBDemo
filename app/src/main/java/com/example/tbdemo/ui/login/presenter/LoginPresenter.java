package com.example.tbdemo.ui.login.presenter;

import com.example.tbdemo.api.LoginApi;
import com.example.tbdemo.base.BaseContract;
import com.example.tbdemo.base.BasePresenter;
import com.example.tbdemo.bean.LoginBean;
import com.example.tbdemo.ui.login.contract.LoginContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.presenter {

    private LoginApi loginApi;

    @Inject
    public LoginPresenter(LoginApi loginApi){
        this.loginApi = loginApi;
    }

    @Override
    public void login(String phone, String pwd) {
        loginApi.login(phone, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                            if (mView !=null){
                                mView.loginSuccess(loginBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
