package com.example.nhattruong.financialmanager.injection.module;

import android.app.Application;

import com.example.nhattruong.financialmanager.interactor.assets.AssetsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AssetsModule {

    @Provides
    @Singleton
    AssetsManager provideAssetsManager(Application application) {
        return new AssetsManager(application);
    }

}
