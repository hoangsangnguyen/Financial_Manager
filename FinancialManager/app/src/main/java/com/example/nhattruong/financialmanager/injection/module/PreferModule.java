package com.example.nhattruong.financialmanager.injection.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    PreferManager providePreferManager(SharedPreferences sharedPreferences) {
        return new PreferManager(sharedPreferences);
    }

}
