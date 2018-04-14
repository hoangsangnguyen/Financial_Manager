package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;

import java.util.List;

public class SpendingsPresenter implements IDetailInteractor.IViewSpendingsInteractor {

    private IDetailInteractor.IViewSpendings iView;
    private SpendingsInteractor spendingsInteractor;

    SpendingsPresenter(IDetailInteractor.IViewSpendings iView) {
        this.iView = iView;
        spendingsInteractor = new SpendingsInteractor(this);
    }

    public void callSpendingsData(ApiServices apiServices) {
        spendingsInteractor.getSpendingsData(apiServices);
    }

    @Override
    public void sendSuccess(List<Spending> spendingList) {
        iView.showSuccess(spendingList);
    }

    @Override
    public void sendFailure() {
        iView.showFailed();
    }
}
