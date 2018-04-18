package com.example.nhattruong.financialmanager.mvp.income;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

import java.util.Date;

public interface CreateIncomeContract {
    interface View extends IBaseView{

        void getAllJarSuccess();

        void createIncomeJarFailed(RestError error);

        void createIncomeJarSuccess();
    }

    interface Presenter {
        void getAllJar();

        void createIncomeForJar(Date date, String detail, double amount);

        void createGeneralIncome(Date date, String detail, double amount);

        void setJarId(String jarId);
    }
}
