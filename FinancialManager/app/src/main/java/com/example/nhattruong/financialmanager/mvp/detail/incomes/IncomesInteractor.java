package com.example.nhattruong.financialmanager.mvp.detail.incomes;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.model.DateIncomes;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomesInteractor implements IDetailInteractor.IChangeListData<DateIncomes, Income> {

    private IDetailInteractor.IViewIncomesInteractor iViewInteractor;
    private List<Income> incomeList;
    private List<DateIncomes> dateIncomesList;

    IncomesInteractor(IDetailInteractor.IViewIncomesInteractor iViewInteractor) {
        this.iViewInteractor = iViewInteractor;
        incomeList = new ArrayList<>();
        dateIncomesList = new ArrayList<>();
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
                    dateIncomesList = changeListData(dateIncomesList, incomeList);
                    iViewInteractor.sendSuccess(dateIncomesList);
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

    @Override
    public boolean checkDate(List<DateIncomes> dateList, String date) {
        for (DateIncomes dateIncomes : dateList) {
            if (dateIncomes.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DateIncomes> changeListData(List<DateIncomes> dateList, List<Income> list) {
        if (list.size() != 0) {
            for (Income income : list) {
                DateIncomes dateIncomes = new DateIncomes();
                List<Income> tempIncomeList = new ArrayList<>();
                dateIncomes.setDate(income.getDate());
                if (dateList.size() != 0) {
                    if (!checkDate(dateList, dateIncomes.getDate())) {
                        for (Income itemIncome : list) {
                            if (itemIncome.getDate().equals(dateIncomes.getDate())) {
                                tempIncomeList.add(itemIncome);
                            }
                        }
                        dateIncomes.setIncomeList(tempIncomeList);
                        dateList.add(dateIncomes);
                    }
                } else {
                    dateList = new ArrayList<>();
                    for (Income itemIncome : list) {
                        if (itemIncome.getDate().equals(dateIncomes.getDate())) {
                            tempIncomeList.add(itemIncome);
                        }
                    }
                    dateIncomes.setIncomeList(tempIncomeList);
                    dateList.add(dateIncomes);
                }
            }
        }
        return dateList;
    }
}
