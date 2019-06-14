package com.example.tbdemo.api;

import com.example.tbdemo.bean.LoginBean;

import io.reactivex.Observable;

public class LoginApi {

    private static LoginApi loginApi;
    private ApiService apiService;

    private LoginApi (ApiService apiService){
        this.apiService = apiService;
    }
    /**
     * 双重检验锁机制
     * @return
     */
    public static LoginApi getLoginApi(ApiService apiService){

        if (loginApi == null){//判空，防止多次创建对象,只适合单线程访问
            synchronized (LoginApi.class){//防止多线程并发导致的数据安全问题
                if (loginApi == null){//防止多个线程，创建多个实例
                    loginApi = new LoginApi(apiService);//自己创建自己的对象
                }
            }
        }
        return loginApi;
    }

    public Observable<LoginBean> login(String phone,String pwd){
        return apiService.login(phone, pwd);
    }

}
