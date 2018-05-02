package com.example.nhattruong.financialmanager.injection.module;

import android.app.Application;

import com.example.nhattruong.financialmanager.interactor.sqlite.SQLiteManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SQLiteModule {
    @Provides
    @Singleton
    SQLiteManager provideSQLiteManager(Application application){
        return new SQLiteManager(application);
    }
}
