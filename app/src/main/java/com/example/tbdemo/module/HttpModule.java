package com.example.tbdemo.module;

import com.example.tbdemo.api.Api;
import com.example.tbdemo.api.ApiService;
import com.example.tbdemo.api.LoginApi;
import com.example.tbdemo.module.interceptor.MyInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {


    @Provides
    OkHttpClient.Builder provideOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        if (Api.isRelease){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        //自定义应用拦截器
        //自定义网络拦截器
        //构建对象成功
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(new MyInterceptor());
    }

    @Provides
    LoginApi provideLoginApi(OkHttpClient.Builder builder){

    Retrofit retrofit = new Retrofit.Builder( )
            .baseUrl(Api.LoginUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build();

    ApiService apiService = retrofit.create(ApiService.class);
    return LoginApi.getLoginApi(apiService);
    }
}
