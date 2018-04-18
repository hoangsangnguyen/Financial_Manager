package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import android.os.Bundle;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.DateSpendings;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.List;

public class SpendingsPresenter implements IDetailInteractor.IViewSpendingsInteractor {

    private IDetailInteractor.IViewSpendings iView;
    private SpendingsInteractor spendingsInteractor;

    SpendingsPresenter(IDetailInteractor.IViewSpendings iView) {
        this.iView = iView;
        spendingsInteractor = new SpendingsInteractor(this);
    }

    public void callSpendingsData(ApiServices apiServices, String token, String userId, String jarId) {
        spendingsInteractor.getSpendingsData(apiServices, token, userId, jarId);
    }

    public void callInfoId(Bundle bundle) {
        spendingsInteractor.getInfoId(bundle);
    }

    @Override
    public void sendIdSuccess(String token, String useId, String jarId) {
        iView.getIdSuccess(token, useId, jarId);
    }

    @Override
    public void sendIdFailure() {
        iView.getIdFailure();
    }

    @Override
    public void sendSuccess(List<DateSpendings> dateSpendingsList) {
        iView.showSuccess(dateSpendingsList);
    }

    @Override
    public void sendFailure() {
        iView.showFailed();
    }
}
