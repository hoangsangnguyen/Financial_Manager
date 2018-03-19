package com.example.nhattruong.financialmanager.interactor.api.network;

import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("user/login")
    Call<UserResponse> login(
            @Body LoginRequest request
    );

    @POST("user/signup")
    Call<UserResponse> signUp(
            @Body SignUpRequest request
    );

    @GET("type/getTypeLists")
    Call<TypeResponse> getTypes(
    );

    @GET("state/getStateLists")
    Call<StateResponse> getStates(
    );

    @GET("jar/getJars/{userName}")
    Call<JarResponse> getJars(
            @Header("token") String token,
            @Query("userName") String userName
    );




}