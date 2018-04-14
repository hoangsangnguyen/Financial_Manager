package com.example.nhattruong.financialmanager.mvp.detail.incomes;

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

    public void callIncomesData(ApiServices apiServices) {
        incomesInteractor.getIncomesData(apiServices);
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
