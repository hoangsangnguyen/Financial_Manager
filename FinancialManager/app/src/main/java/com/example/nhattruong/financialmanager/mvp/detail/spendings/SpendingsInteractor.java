package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.response.SpendingResponse;
import com.example.nhattruong.financialmanager.model.DateSpendings;
import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpendingsInteractor implements IDetailInteractor.IChangeListData<DateSpendings, Spending> {

    private IDetailInteractor.IViewSpendingsInteractor iViewInteractor;
    private List<Spending> spendingList;
    private List<DateSpendings> dateSpendingsList;

    SpendingsInteractor(IDetailInteractor.IViewSpendingsInteractor iViewInteractor) {
        this.iViewInteractor = iViewInteractor;
        spendingList = new ArrayList<>();
        dateSpendingsList = new ArrayList<>();
    }

    public void getSpendingsData(ApiServices apiServices) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InNhbmduZ3V5ZW4xIiwibmJmIjoxNTIzMjc4ODk3LCJleHAiOjE1MjM4ODM2OTcsImlhdCI6MTUyMzI3ODg5NywiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo1MDE5MSIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAxOTEifQ.dIqupPJOs7h1LMAT2SetGyXeMTnMbQ-MPc65SalnYl8";
        String userId = "5f180dd6-2c2c-4df3-989e-846e960a67d5";
        String jarId = "cf4d6a28-24dd-4740-8bd3-0f703833315d";
        Call<SpendingResponse> call = apiServices.getSpendingResponse(token, userId, jarId);
        call.enqueue(new Callback<SpendingResponse>() {
            @Override
            public void onResponse(Call<SpendingResponse> call, Response<SpendingResponse> response) {
                if (response.body() != null) {
                    spendingList.addAll(response.body().getSpendings());
                    dateSpendingsList = changeListData(dateSpendingsList, spendingList);
                    iViewInteractor.sendSuccess(dateSpendingsList);
                } else {
                    iViewInteractor.sendFailure();
                }
            }

            @Override
            public void onFailure(Call<SpendingResponse> call, Throwable t) {
                iViewInteractor.sendFailure();
            }
        });
    }

    @Override
    public boolean checkDate(List<DateSpendings> dateList, String date) {
        for (DateSpendings dateSpendings : dateList) {
            if (dateSpendings.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DateSpendings> changeListData(List<DateSpendings> dateList, List<Spending> list) {
        if (list.size() != 0) {
            for (Spending spending : list) {
                DateSpendings dateSpendings = new DateSpendings();
                List<Spending> tempSpendingList = new ArrayList<>();
                dateSpendings.setDate(spending.getDate());
                if (dateList.size() != 0) {
                    if (!checkDate(dateList, dateSpendings.getDate())) {
                        for (Spending itemSpending : list) {
                            if (itemSpending.getDate().equals(dateSpendings.getDate())) {
                                tempSpendingList.add(itemSpending);
                            }
                        }
                        dateSpendings.setSpendingList(tempSpendingList);
                        dateList.add(dateSpendings);
                    }
                } else {
                    dateList = new ArrayList<>();
                    for (Spending itemSpending : list) {
                        if (itemSpending.getDate().equals(dateSpendings.getDate())) {
                            tempSpendingList.add(itemSpending);
                        }
                    }
                    dateSpendings.setSpendingList(tempSpendingList);
                    dateList.add(dateSpendings);
                }
            }
        }
        return dateList;
    }
}
