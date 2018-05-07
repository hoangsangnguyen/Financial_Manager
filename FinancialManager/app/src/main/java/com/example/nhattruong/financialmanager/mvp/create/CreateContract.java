package com.example.nhattruong.financialmanager.mvp.create;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

import java.util.Date;

public interface CreateContract {
    interface View extends IBaseView{

        void getAllJarSuccess();

        void onFailure(RestError error);

        void createSuccess();
    }

    interface Presenter {
        void getAllJar();

        void createIncomeForJar(Date date, String detail, double amount);

        void createGeneralIncome(Date date, String detail, double amount);

        void createSpending(Date date, String detail, double amount);

        void createDebt(Date date, String detail, double amount, String origin, String state, boolean isPositive);

        void setJarId(String jarId);

    }
}
