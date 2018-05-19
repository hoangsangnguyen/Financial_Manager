package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.Debt;

import java.util.Date;

public interface DebtContract {
    interface View extends IBaseView{
        void onSuccess();

        void onFailure(RestError error);

        void getAllDebt(String jarId);

        void filterDebt(Date dateFrom, Date dateTo);

    }

    interface Presenter{

        void getDebts();

        void deleteDebt(int positionGroup, int positionChild);

        void updateDebt(Debt debt);
    }
}
