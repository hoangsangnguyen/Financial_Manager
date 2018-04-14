package com.example.nhattruong.financialmanager.mvp.detail.incomes;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomesInteractor {

    private IDetailInteractor.IViewIncomesInteractor iViewInteractor;
    private List<Income> incomeList;

    IncomesInteractor(IDetailInteractor.IViewIncomesInteractor iViewInteractor) {
        this.iViewInteractor = iViewInteractor;
        incomeList = new ArrayList<>();
    }

    public void getIncomesData(ApiServices apiServices) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InNhbmduZ3V5ZW4xIiwibmJmIjoxNTIzMjc4ODk3LCJleHAiOjE1MjM4ODM2OTcsImlhdCI6MTUyMzI3ODg5NywiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo1MDE5MSIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAxOTEifQ.dIqupPJOs7h1LMAT2SetGyXeMTnMbQ-MPc65SalnYl8";
        String userId = "5f180dd6-2c2c-4df3-989e-846e960a67d5";
        String jarId = "cf4d6a28-24dd-4740-8bd3-0f703833315d";
        Call<IncomeResponse> call = apiServices.getIncomeResponse(token, userId, jarId);
        call.enqueue(new Callback<IncomeResponse>() {
            @Override
            public void onResponse(Call<IncomeResponse> call, Response<IncomeResponse> response) {
                if (response.body() != null) {
                    incomeList.addAll(response.body().getIncomes());
                    iViewInteractor.sendSuccess(incomeList);
                } else {
                    iViewInteractor.sendFailure();
                }
            }

            @Override
            public void onFailure(Call<IncomeResponse> call, Throwable t) {
                iViewInteractor.sendFailure();
            }
        });
    }
}
