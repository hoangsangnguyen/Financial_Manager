package com.example.nhattruong.financialmanager.interactor.api;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.network.RestCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.interactor.event.EventManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.model.Jar;

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
    public void login(final LoginRequest request, final ApiCallback<UserResponse> callback) {
        mApiServices.login(request)
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

    //======================== get Types ==========================
    public void getTypes(final ApiCallback<TypeResponse> callback) {
        mApiServices.getTypes()
                .enqueue(new RestCallback<TypeResponse>() {
                    @Override
                    public void success(TypeResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

    //======================== get States ==========================
    public void getStates(final ApiCallback<StateResponse> callback) {
        mApiServices.getStates()
                .enqueue(new RestCallback<StateResponse>() {
                    @Override
                    public void success(StateResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

    //======================== get Jars ==========================
    public void getJars(String token, String userName, final ApiCallback<JarResponse> callback) {
        mApiServices.getJars(token, userName)
                .enqueue(new RestCallback<JarResponse>() {
                    @Override
                    public void success(JarResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

    public void getJarByID(String token, int typeID, String userName, final ApiCallback<JarResponse> callback) {
        mApiServices.getJarByID(token, typeID, userName)
                .enqueue(new RestCallback<JarResponse>() {
                    @Override
                    public void success(JarResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

    public void updateJars(String token, Jar jar, final ApiCallback<JarResponse> callback) {
        mApiServices.updateJars(token, jar)
                .enqueue(new RestCallback<JarResponse>() {
                    @Override
                    public void success(JarResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

}