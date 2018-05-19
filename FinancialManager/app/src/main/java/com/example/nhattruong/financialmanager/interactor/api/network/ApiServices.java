package com.example.nhattruong.financialmanager.interactor.api.network;

import com.example.nhattruong.financialmanager.interactor.api.request.CreateDebtRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateIncomeSpendingRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateTodoRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.DebtUpdateRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.StatisticRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.CreateTodoResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.DebtResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.ImageResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.SpendingResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StatisticResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TodoResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UpdateDebtResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.model.Jar;
import com.example.nhattruong.financialmanager.model.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("users/login")
    Call<UserResponse> login(
            @Body LoginRequest request
    );

    @POST("users")
    Call<UserResponse> signUp(
            @Body SignUpRequest request
    );

    @GET("users/{userId}")
    Call<UserResponse> getUser(
            @Header("token") String token,
            @Path("userId") String userId
    );

    @PUT("users/{userId}")
    Call<BaseResponse> updateUser(
            @Header("token") String token,
            @Path("userId") String userId,
            @Body User user
    );

    @GET("types")
    Call<TypeResponse> getTypes(
    );

    @GET("state/getStateLists")
    Call<StateResponse> getStates(
    );

    @GET("users/{userId}/jars")
    Call<JarResponse> getJars(
            @Header("token") String token,
            @Path("userId") String userId
    );

    @GET("jar/getJarByID/{typeId}/{userName}")
    Call<JarResponse> getJarByID(
            @Header("token") String token,
            @Query("typeId") int typeId,
            @Query("userName") String userName
    );

    @PUT("jar/updateJars")
    Call<JarResponse> updateJars(
            @Header("token") String token,
            @Body Jar jar
    );

    @POST("users/{userId}/jars/{jarId}/incomes")
    Call<BaseResponse> createIncomeForJar(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Body CreateIncomeSpendingRequest request
    );

    @POST("users/{userId}/incomes")
    Call<BaseResponse> createGeneralIncome(
            @Header("token") String token,
            @Path("userId") String user,
            @Body CreateIncomeSpendingRequest request
    );

    @POST("users/{userId}/jars/{jarId}/spendings")
    Call<BaseResponse> createSpending(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Body CreateIncomeSpendingRequest request
    );

    @GET("users/{userId}/jars/{jarId}/spendings")
    Call<SpendingResponse> getAllSpending(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId
    );

    @GET("users/{userId}/jars/{jarId}/spendings")
    Call<SpendingResponse> filterSpending(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Query("from") String dateFrom,
            @Query("to") String dateTo
    );

    @DELETE("users/{userId}/jars/{jarId}/spendings/{spendingId}")
    Call<BaseResponse> deleteSpending(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Path("spendingId") String spendingId
    );

    @GET("users/{userId}/jars/{jarId}/incomes")
    Call<IncomeResponse> getAllIncome(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId
    );

    @GET("users/{userId}/jars/{jarId}/incomes")
    Call<IncomeResponse> filterIncome(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Query("from") String dateFrom,
            @Query("to") String dateTo
    );

    @GET("users/{userId}/jars/{jarId}/debts")
    Call<DebtResponse> getAllDebt(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId
    );

    @GET("users/{userId}/jars/{jarId}/debts")
    Call<DebtResponse> filterDebt(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Query("from") String dateFrom,
            @Query("to") String dateTo
    );

    @POST("users/{userId}/jars/{jarId}/debts")
    Call<BaseResponse> createDebt(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Body CreateDebtRequest request
    );

    @DELETE("users/{userId}/jars/{jarId}/debts/{debtId}")
    Call<BaseResponse> deleteDebt(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Path("debtId") String debtId
    );

    @PUT("users/{userId}/jars/{jarId}/debts/{debtId}")
    Call<UpdateDebtResponse> updateDebt(
            @Header("token") String token,
            @Path("userId") String userId,
            @Path("jarId") String jarId,
            @Path("debtId") String debtId,
            @Body DebtUpdateRequest request
    );

    @Multipart
    @POST("users/{userId}/images")
    Call<ImageResponse> uploadImage(
            @Header("token") String token,
            @Path("userId") String usreId,
            @Part MultipartBody.Part file
    );

    @POST("users/{userId}/statistics/months")
    Call<StatisticResponse> getStatistic(
            @Header("token") String token,
            @Path("userId") String userId,
            @Body StatisticRequest request
    );

    @POST("users/{userID}/notifications")
    Call<CreateTodoResponse> createTodo(
            @Header("token") String token,
            @Path("userID") String userID,
            @Body CreateTodoRequest request
    );

    @GET("users/{userID}/notifications/next")
    Call<TodoResponse> getTodoNext(
            @Header("token") String token,
            @Path("userID") String userID
    );

    String URL = "http://sixfinancialbox.azurewebsites.net/api/";

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