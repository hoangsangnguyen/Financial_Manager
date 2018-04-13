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

    // ==== GET TOKEN == //
    public String getToken() {
        if (mPreferManager.getToken() == null) return null;
        return mPreferManager.getToken();
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

    //======================== Get User ==========================
    public void getUser(String userId, final ApiCallback<UserResponse> callback){
        mApiServices.getUser(getToken(), userId)
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
    public void getJars(String id, final ApiCallback<JarResponse> callback) {
        mApiServices.getJars(getToken(), id)
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

    public void getJarByID(int typeID, String userName, final ApiCallback<JarResponse> callback) {
        mApiServices.getJarByID(getToken(), typeID, userName)
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

    public void updateJars(Jar jar, final ApiCallback<JarResponse> callback) {
        mApiServices.updateJars(getToken(), jar)
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