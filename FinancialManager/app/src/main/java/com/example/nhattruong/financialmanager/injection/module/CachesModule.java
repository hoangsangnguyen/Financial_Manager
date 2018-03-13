package com.example.nhattruong.financialmanager.injection.module;

import android.app.Application;


import com.example.nhattruong.financialmanager.interactor.caches.CachesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CachesModule {
    @Provides
    @Singleton
    CachesManager provideCachesManager(Application application) {
        return new CachesManager(application);
    }
}
