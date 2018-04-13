package com.example.nhattruong.financialmanager.injection.module;

import com.example.nhattruong.financialmanager.interactor.api.ApiManager;
import com.example.nhattruong.financialmanager.interactor.event.EventManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ApiManager provideApiManager(@Named("api") Retrofit retrofit, PreferManager preferManager, EventManager eventManager) {
        return new ApiManager(retrofit, preferManager, eventManager);
    }
}
