package com.example.nhattruong.financialmanager.mvp.detail.debts;

import android.os.Bundle;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.DateDebts;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.List;

public class DebtsPresenter implements IDetailInteractor.IViewDebtsInteractor {

    private IDetailInteractor.IViewDebts iViewDebts;
    private DebtsInteractor debtsInteractor;

    DebtsPresenter(IDetailInteractor.IViewDebts iViewDebts) {
        this.iViewDebts = iViewDebts;
        debtsInteractor = new DebtsInteractor(this);
    }

    public void callDebtsData(ApiServices apiServices, String token, String userId, String jarId) {
        debtsInteractor.getDebtsData(apiServices, token, userId, jarId);
    }

    public void callInfoId(Bundle bundle){
        debtsInteractor.getInfoId(bundle);
    }

    @Override
    public void sendIdSuccess(String token, String useId, String jarId) {
        iViewDebts.getIdSuccess(token, useId, jarId);
    }

    @Override
    public void sendIdFailure() {
iViewDebts.getIdFailure();
    }

    @Override
    public void sendSuccess(List<DateDebts> dateDebtsList) {
        iViewDebts.showSuccess(dateDebtsList);
    }

    @Override
    public void sendFailure() {
        iViewDebts.showFailed();
    }
}
