package com.example.tbdemo.base;

import java.util.Properties;

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>{

    protected T mView;

    @Override
    public void attchView(T view) {
        this.mView = view;
    }

    @Override
    public void datechView() {
        if (mView != null){
            mView = null;
        }
    }
}
