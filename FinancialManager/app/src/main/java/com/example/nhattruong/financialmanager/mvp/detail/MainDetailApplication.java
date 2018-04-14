package com.example.nhattruong.financialmanager.mvp.detail;

import android.app.Application;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainDetailApplication {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiServices.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
