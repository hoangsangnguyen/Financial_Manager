package com.example.nhattruong.financialmanager.mvp.income;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

import java.util.Date;

/**
 * Created by nhattruong on 4/11/2018.
 */

public interface CreateIncomeContract {
    interface View extends IBaseView{

        void getAllJarSuccess();

        void createIncomeForJarFailed(RestError error);

        void createIncomeForJarSuccess();
    }

    interface Presenter {
        void getAllJar();

        void createIncomeForJar(Date date, String detail, int amount);

        void setJarId(String jarId);
    }
}
