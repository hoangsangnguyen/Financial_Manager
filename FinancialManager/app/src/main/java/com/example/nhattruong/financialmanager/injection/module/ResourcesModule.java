package com.example.nhattruong.financialmanager.injection.module;

import android.app.Application;

import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.interactor.resources.ResourcesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ResourcesModule {

    @Provides
    @Singleton
    ResourcesManager provideResourcesManager(Application application, PreferManager preferManager) {
        return new ResourcesManager(application, preferManager);
    }

}
