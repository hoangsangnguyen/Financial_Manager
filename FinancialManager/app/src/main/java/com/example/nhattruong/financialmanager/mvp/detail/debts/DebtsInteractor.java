package com.example.nhattruong.financialmanager.mvp.detail.debts;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.response.DebtResponse;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DebtsInteractor {

    private IDetailInteractor.IViewDebtsInteractor iViewDebtsInteractor;
    private List<Debt> debtList;

    DebtsInteractor(IDetailInteractor.IViewDebtsInteractor iViewDebtsInteractor) {
        this.iViewDebtsInteractor = iViewDebtsInteractor;
        debtList = new ArrayList<>();
    }

    public void getDebtsData(ApiServices apiServices) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InNhbmduZ3V5ZW4xIiwibmJmIjoxNTIzMjc4ODk3LCJleHAiOjE1MjM4ODM2OTcsImlhdCI6MTUyMzI3ODg5NywiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo1MDE5MSIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAxOTEifQ.dIqupPJOs7h1LMAT2SetGyXeMTnMbQ-MPc65SalnYl8";
        String userId = "5f180dd6-2c2c-4df3-989e-846e960a67d5";
        String jarId = "cf4d6a28-24dd-4740-8bd3-0f703833315d";
        Call<DebtResponse> call = apiServices.getDebtResponse(token, userId, jarId);
        call.enqueue(new Callback<DebtResponse>() {
            @Override
            public void onResponse(Call<DebtResponse> call, Response<DebtResponse> response) {
                if (response.body() != null) {
                    debtList.addAll(response.body().getDebts());
                    iViewDebtsInteractor.sendSuccess(debtList);
                } else {
                    iViewDebtsInteractor.sendFailure();
                }
            }

            @Override
            public void onFailure(Call<DebtResponse> call, Throwable t) {
                iViewDebtsInteractor.sendFailure();
            }
        });
    }
}
