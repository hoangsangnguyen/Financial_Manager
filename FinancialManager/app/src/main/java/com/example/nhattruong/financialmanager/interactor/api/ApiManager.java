package com.example.nhattruong.financialmanager.interactor.api;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.network.RestCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.interactor.event.EventManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    //======================== Login ==========================
    public void login(final LoginRequest request, final ApiCallback<BaseResponse> callback) {
        mApiServices.login(request)
                .enqueue(new RestCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }

                });
    }

    //======================== Sign up ==========================
    public void signUp(SignUpRequest request, final ApiCallback<UserResponse> callback) {
        mApiServices.signUp(request)
                .enqueue(new RestCallback<UserResponse>() {
                    @Override
                    public void success(UserResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }
}