package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import android.os.Bundle;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.response.SpendingResponse;
import com.example.nhattruong.financialmanager.model.DateSpendings;
import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpendingsInteractor extends BasePresenter implements IDetailInteractor.IChangeListData<DateSpendings, Spending> {

    private IDetailInteractor.IViewSpendingsInteractor iViewInteractor;
    private List<Spending> spendingList;
    private List<DateSpendings> dateSpendingsList;

    SpendingsInteractor(IDetailInteractor.IViewSpendingsInteractor iViewInteractor) {
        this.iViewInteractor = iViewInteractor;
        spendingList = new ArrayList<>();
        dateSpendingsList = new ArrayList<>();
    }

    public void getSpendingsData(ApiServices apiServices, String token, String userId, String jarId) {
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

    public void getInfoId(Bundle bundle) {
        if (bundle != null) {
            String jarId = bundle.getString("JAR_ID");
            String token = getSQLiteManager().getToken();
            String userId = getSQLiteManager().getUser().getId();
            iViewInteractor.sendIdSuccess(token, userId, jarId);
        } else {
            iViewInteractor.sendIdFailure();
        }
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
                    /*if (!checkDate(dateList, dateSpendings.getDate())) {
                        for (Spending itemSpending : list) {
                            if (itemSpending.getDate().equals(dateSpendings.getDate())) {
                                tempSpendingList.add(itemSpending);
                            }
                        }
                        dateSpendings.setSpendingList(tempSpendingList);
                        dateList.add(dateSpendings);
                    }*/
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
