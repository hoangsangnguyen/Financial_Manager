package com.example.nhattruong.financialmanager.mvp.detail.debts;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.DateDebts;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.List;

public class DebtsPresenter implements IDetailInteractor.IViewDebtsInteractor {

    private IDetailInteractor.IViewDebts iViewDebts;
    private DebtsInteractor debtsInteractor;

    DebtsPresenter(IDetailInteractor.IViewDebts iViewDebts) {
        this.iViewDebts = iViewDebts;
        debtsInteractor = new DebtsInteractor(this);
    }

    public void callDebtsData(ApiServices apiServices) {
        debtsInteractor.getDebtsData(apiServices);
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
