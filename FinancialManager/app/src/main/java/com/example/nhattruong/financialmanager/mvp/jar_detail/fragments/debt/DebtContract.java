package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

public interface DebtContract {
    interface View extends IBaseView{
        void getAllDebtSuccess();

        void getAllDebtFailure(RestError error);

        void getAllDebt(String jarId);
    }

    interface Presenter{
        void getAllDebt();
    }
}
