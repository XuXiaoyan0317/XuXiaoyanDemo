package com.lanou3g.liwushuodemo.Base;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/5/18.
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
