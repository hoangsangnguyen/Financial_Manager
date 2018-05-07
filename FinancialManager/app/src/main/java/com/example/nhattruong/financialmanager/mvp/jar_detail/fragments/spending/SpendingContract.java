package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

import java.util.Date;

public interface SpendingContract {
    interface View extends IBaseView{
        void getSpendingSuccess();

        void onFailure(RestError error);

        void getAllSpending();

        void filterSpending(Date dateStart, Date dateEnd);

        void deleteSpendingSuccess();

    }

    interface Presenter{

        void getSpending();

        void deleteSpending(int positionGroup, int positionChild);
    }
}
