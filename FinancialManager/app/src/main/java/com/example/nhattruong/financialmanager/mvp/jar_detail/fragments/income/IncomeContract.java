package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

public interface IncomeContract {
    interface View extends IBaseView {
        void getIncomeSuccess();

        void getIncomeFailure(RestError error);

        void getAllIncome(String jarId);
    }

    interface Presenter{
        void getAllIncome();
    }
}
