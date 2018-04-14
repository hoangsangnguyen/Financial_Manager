package com.example.nhattruong.financialmanager.interactor.api.network;

import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.DebtResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.SpendingResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.model.Jar;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("api/users/login")
    Call<UserResponse> login(
            @Body LoginRequest request
    );

    @POST("api/users")
    Call<UserResponse> signUp(
            @Body SignUpRequest request
    );

    @GET("api/users/{userId}")
    Call<UserResponse> getUser(
            @Header("token") String token,
            @Path("userId") String userId
    );

    @GET("api/types")
    Call<TypeResponse> getTypes(
    );

    @GET("api/state/getStateLists")
    Call<StateResponse> getStates(
    );

    @GET("api/users/{userId}/jars")
    Call<JarResponse> getJars(
            @Header("token") String token,
            @Path("userId") String userId
    );

    @GET("api/jar/getJarByID/{typeId}/{userName}")
    Call<JarResponse> getJarByID(
            @Header("token") String token,
            @Query("typeId") int typeId,
            @Query("userName") String userName
    );

    @PUT("api/jar/updateJars")
    Call<JarResponse> updateJars(
            @Header("token") String token,
            @Body Jar jar
    );

    String URL = "http://sixfinancialboxs.azurewebsites.net/api/";

    @GET("users/{userId}/jars/{jarId}/spendings")
    Call<SpendingResponse> getSpendingResponse(@Header("token") String token, @Path("userId") String userId,
                                               @Path("jarId") String jarId);

    @GET("users/{userId}/jars/{jarId}/incomes")
    Call<IncomeResponse> getIncomeResponse(@Header("token") String token, @Path("userId") String userId,
                                           @Path("jarId") String jarId);

    @GET("users/{userId}/jars/{jarId}/debts")
    Call<DebtResponse> getDebtResponse(@Header("token") String token, @Path("userId") String userId,
                                       @Path("jarId") String jarId);

}