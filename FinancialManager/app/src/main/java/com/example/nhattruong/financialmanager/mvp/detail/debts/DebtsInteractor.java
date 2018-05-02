package com.example.nhattruong.financialmanager.mvp.detail.debts;

import android.os.Bundle;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.interactor.api.response.DebtResponse;
import com.example.nhattruong.financialmanager.model.DateDebts;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DebtsInteractor extends BasePresenter implements IDetailInteractor.IChangeListData<DateDebts, Debt> {

    private IDetailInteractor.IViewDebtsInteractor iViewDebtsInteractor;
    private List<Debt> debtList;
    private List<DateDebts> dateDebtsList;

    DebtsInteractor(IDetailInteractor.IViewDebtsInteractor iViewDebtsInteractor) {
        this.iViewDebtsInteractor = iViewDebtsInteractor;
        debtList = new ArrayList<>();
        dateDebtsList = new ArrayList<>();
    }

    public void getDebtsData(ApiServices apiServices, String token, String userId, String jarId) {
        Call<DebtResponse> call = apiServices.getDebtResponse(token, userId, jarId);
        call.enqueue(new Callback<DebtResponse>() {
            @Override
            public void onResponse(Call<DebtResponse> call, Response<DebtResponse> response) {
                if (response.body() != null) {
                    debtList.addAll(response.body().getDebts());
                    dateDebtsList = changeListData(dateDebtsList, debtList);
                    iViewDebtsInteractor.sendSuccess(dateDebtsList);
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

    public void getInfoId(Bundle bundle) {
        if (bundle != null) {
            String jarId = bundle.getString("JAR_ID");
            String token = getSQLiteManager().getToken();
            String userId = getSQLiteManager().getUser().getId();
            iViewDebtsInteractor.sendIdSuccess(token, userId, jarId);
        } else {
            iViewDebtsInteractor.sendIdFailure();
        }
    }

    @Override
    public boolean checkDate(List<DateDebts> dateList, String date) {
        for (DateDebts dateDebts : dateList) {
            if (dateDebts.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DateDebts> changeListData(List<DateDebts> dateList, List<Debt> list) {
        if (list.size() != 0) {
            for (Debt debt : list) {
                DateDebts dateDebts = new DateDebts();
                List<Debt> tempDebtList = new ArrayList<>();
                dateDebts.setDate(debt.getDate());
                if (dateList.size() != 0) {
                   /* if (!checkDate(dateList, dateDebts.getDate())) {
                        for (Debt itemDebt : list) {
                            if (itemDebt.getDate().equals(dateDebts.getDate())) {
                                tempDebtList.add(itemDebt);
                            }
                        }
                        dateDebts.setDebtList(tempDebtList);
                        dateList.add(dateDebts);
                    }*/
                } else {
                    dateList = new ArrayList<>();
                    for (Debt itemDebt : list) {
                        if (itemDebt.getDate().equals(dateDebts.getDate())) {
                            tempDebtList.add(itemDebt);
                        }
                    }
                    dateDebts.setDebtList(tempDebtList);
                    dateList.add(dateDebts);
                }
            }
        }
        return dateList;
    }
}
