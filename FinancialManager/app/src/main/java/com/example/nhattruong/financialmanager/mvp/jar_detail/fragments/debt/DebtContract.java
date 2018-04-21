package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.Debt;

public interface DebtContract {
    interface View extends IBaseView{
        void onSuccess();

        void onFailure(RestError error);

        void getAllDebt(String jarId);

    }

    interface Presenter{
        void getAllDebt();

        void deleteDebt(int positionGroup, int positionChild);

        void updateDebt(Debt debt);
    }
}
