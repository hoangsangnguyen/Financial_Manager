package com.example.nhattruong.financialmanager.injection.module;

import com.example.nhattruong.financialmanager.interactor.api.ApiManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.interactor.sqlite.SQLiteManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ApiManager provideApiManager(@Named("api") Retrofit retrofit, PreferManager preferManager, SQLiteManager sqLiteManager) {
        return new ApiManager(retrofit, preferManager, sqLiteManager);
    }
}
