package com.example.nhattruong.financialmanager.mvp.detail.incomes;

import android.os.Bundle;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.model.DateIncomes;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomesInteractor extends BasePresenter implements IDetailInteractor.IChangeListData<DateIncomes, Income> {

    private IDetailInteractor.IViewIncomesInteractor iViewInteractor;
    private List<Income> incomeList;
    private List<DateIncomes> dateIncomesList;

    IncomesInteractor(IDetailInteractor.IViewIncomesInteractor iViewInteractor) {
        this.iViewInteractor = iViewInteractor;
        incomeList = new ArrayList<>();
        dateIncomesList = new ArrayList<>();
    }

    public void getIncomesData(ApiServices apiServices, String token, String userId, String jarId) {
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

    public void getInfoId(Bundle bundle) {
        if (bundle != null) {
            String jarId = bundle.getString("JAR_ID");
            String token = getSQLiteManager().getToken();
            String userId = getSQLiteManager().getUser().getId();
            iViewInteractor.sendIdSuccess(token, userId, jarId);
        }else {
            iViewInteractor.sendIdFailure();
        }
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
                    /*if (!checkDate(dateList, dateIncomes.getDate())) {
                        for (Income itemIncome : list) {
                            if (itemIncome.getDate().equals(dateIncomes.getDate())) {
                                tempIncomeList.add(itemIncome);
                            }
                        }
                        dateIncomes.setIncomeList(tempIncomeList);
                        dateList.add(dateIncomes);
                    }*/
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
