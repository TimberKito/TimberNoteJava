package com.bochi.kito.timbernotejava;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}