package com.sph.android;

import android.app.Application;

import io.realm.Realm;

public class SPHDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
