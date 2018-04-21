package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.Debt;

public interface DebtContract {
    interface View extends IBaseView{
        void getAllDebtSuccess();

        void onFailure(RestError error);

        void getAllDebt(String jarId);

        void deleteDebtSuccess();

        void updateDebtSuccess();
    }

    interface Presenter{
        void getAllDebt();

        void deleteDebt(int positionGroup, int positionChild);

        void updateDebt(Debt debt);
    }
}
