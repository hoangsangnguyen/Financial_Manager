package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

public interface SpendingContract {
    interface View extends IBaseView{
        void getSpendingSuccess();

        void onFailure(RestError error);

        void getAllSpending(String jarId);

        void deleteSpendingSuccess();

    }

    interface Presenter{
        void getAllSpending();

        void deleteSpending(int positionGroup, int positionChild);
    }
}
