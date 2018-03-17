package com.example.nhattruong.financialmanager.interactor.api.network;

import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("user/login")
    Call<BaseResponse> login(
            @Body LoginRequest request
    );

    @POST("user/signup")
    Call<UserResponse> signUp(
            @Body SignUpRequest request
    );
}