package com.example.tbdemo.base;

public interface BaseContract {


    interface BasePresenter<T extends BaseView> {
        void attchView(T view);
        void datechView();
    }

    interface BaseView{
//        void showLoading();
////        void dismissLoding();
    }
}
