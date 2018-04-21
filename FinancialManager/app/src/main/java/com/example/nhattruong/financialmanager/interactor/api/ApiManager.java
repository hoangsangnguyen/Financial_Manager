package com.example.nhattruong.financialmanager.interactor.api;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.network.RestCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateIncomeJarRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.DebtUpdateRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.DebtResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.SpendingResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UpdateDebtResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.interactor.event.EventManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.model.Jar;

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
    public void getUser(String userId, final ApiCallback<UserResponse> callback) {
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

    public void createIncomeForJar(String userId, String jarId, CreateIncomeJarRequest request, final ApiCallback<BaseResponse> callback) {
        mApiServices.createIncomeForJar(getToken(), userId, jarId, request)
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

    public void createGeneralIncome(String userId, CreateIncomeJarRequest request, final ApiCallback<BaseResponse> callback) {
        mApiServices.createGeneralIncome(getToken(), userId, request)
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

    public void getAllSpending(String userId, String jarId, final ApiCallback<SpendingResponse> callback) {
        mApiServices.getAllSpending(getToken(), userId, jarId)
                .enqueue(new RestCallback<SpendingResponse>() {
                    @Override
                    public void success(SpendingResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

    public void deleteSpending(String userId, String jarId, String spendingId, final ApiCallback<BaseResponse> callback) {
        mApiServices.deleteSpending(getToken(), userId, jarId, spendingId)
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

    public void getAllIncome(String userId, String debtId, final ApiCallback<IncomeResponse> callback) {
        mApiServices.getAllIncome(getToken(), userId, debtId)
                .enqueue(new RestCallback<IncomeResponse>() {
                    @Override
                    public void success(IncomeResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

    public void deleteDebt(String userId, String jarId, String debtId, final ApiCallback<BaseResponse> callback) {
        mApiServices.deleteDebt(getToken(), userId, jarId, debtId)
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

    public void updateDebt(String userId, String jarId, String debtId, DebtUpdateRequest request, final ApiCallback<UpdateDebtResponse> callback) {
        mApiServices.updateDebt(getToken(), userId, jarId, debtId, request)
                .enqueue(new RestCallback<UpdateDebtResponse>() {
                    @Override
                    public void success(UpdateDebtResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

    public void getAllDebt(String userId, String jarId, final ApiCallback<DebtResponse> callback) {
        mApiServices.getAllDebt(getToken(), userId, jarId)
                .enqueue(new RestCallback<DebtResponse>() {
                    @Override
                    public void success(DebtResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }
                });
    }

}