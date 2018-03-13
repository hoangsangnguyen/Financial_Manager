package com.example.nhattruong.financialmanager.interactor.api;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.event.EventManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;

import retrofit2.Retrofit;

public class ApiManager {

    private ApiServices mApiServices;

    private PreferManager mPreferManager;
    private EventManager mEventManager;


    public ApiManager(Retrofit retrofit, PreferManager preferManager, EventManager eventManager) {
        mApiServices = retrofit.create(ApiServices.class);
        mPreferManager = preferManager;
        mEventManager = eventManager;
    }
}