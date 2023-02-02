package com.example.cs310news;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsApp extends Application {

    public ExecutorService srv = Executors.newCachedThreadPool();

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.d("BARS", "onCreate: Server started!!!");
    }
}
