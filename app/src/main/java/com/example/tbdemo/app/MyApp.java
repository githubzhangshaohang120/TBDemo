package com.example.tbdemo.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


public class MyApp extends Application {


    private static SharedPreferences sharedPreferences;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        shared();
    }

    private void shared() {
            sharedPreferences = context.getSharedPreferences("config.xml", Context.MODE_PRIVATE);
    }

    public static SharedPreferences getShared(){
        return sharedPreferences;
    }

    public static Context getContext(){
        return context;
    }


}
