package com.example.tbdemo.api;

public interface Api {

    public final boolean isRelease = false;
    //用户

    //登录接口
    public final String  LoginUrl = isRelease?"http://mobile.bwstudent.com/small/":"http://172.17.8.100/small/";//user/v1/login
    //注册接口
    public  final String RegisterUrl = isRelease?"http://mobile.bwstudent.com/small/":"http://172.17.8.100/small/";//user/v1/register
}
