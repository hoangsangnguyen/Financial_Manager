package com.example.nhattruong.financialmanager;

import android.app.Application;

import com.example.nhattruong.financialmanager.injection.component.ApplicationComponent;
import com.example.nhattruong.financialmanager.injection.component.DaggerApplicationComponent;
import com.example.nhattruong.financialmanager.injection.module.ApiModule;
import com.example.nhattruong.financialmanager.injection.module.ApplicationModule;
import com.example.nhattruong.financialmanager.injection.module.NetworkModule;
import com.example.nhattruong.financialmanager.injection.module.PreferModule;
import com.example.nhattruong.financialmanager.injection.module.SQLiteModule;

public class MainApplication extends Application {

    static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (appComponent == null) {
            appComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .apiModule(new ApiModule())
                    .networkModule(new NetworkModule())
                    .preferModule(new PreferModule())
                    .sQLiteModule(new SQLiteModule())
                    .build();
        }
        getAppComponent().inject(this);

    }

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }

}