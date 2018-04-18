package com.example.nhattruong.financialmanager.mvp.detail.incomes;

import android.os.Bundle;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.DateIncomes;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.List;

public class IncomesPresenter implements IDetailInteractor.IViewIncomesInteractor {

    private IDetailInteractor.IViewIncomes iViewIncomes;
    private IncomesInteractor incomesInteractor;

    IncomesPresenter(IDetailInteractor.IViewIncomes iViewIncomes) {
        this.iViewIncomes = iViewIncomes;
        incomesInteractor = new IncomesInteractor(this);
    }

    public void callIncomesData(ApiServices apiServices, String token, String userId, String jarId) {
        incomesInteractor.getIncomesData(apiServices, token, userId, jarId);
    }

    public void callInfoId(Bundle bundle){
        incomesInteractor.getInfoId(bundle);
    }

    @Override
    public void sendIdSuccess(String token, String useId, String jarId) {
        iViewIncomes.getIdSuccess(token, useId, jarId);
    }

    @Override
    public void sendIdFailure() {
        iViewIncomes.getIdFailure();
    }

    @Override
    public void sendSuccess(List<DateIncomes> dateIncomesList) {
        iViewIncomes.showSuccess(dateIncomesList);
    }

    @Override
    public void sendFailure() {
        iViewIncomes.showFailed();
    }
}
