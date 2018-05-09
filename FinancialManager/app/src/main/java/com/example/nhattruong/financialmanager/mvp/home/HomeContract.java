package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.StatisticResponse;

public interface HomeContract {

    interface View extends IBaseView{

        void onLoadJarsSuccess();

        void getStatisticSuccess(StatisticResponse response);

        void onFailure(RestError error);

    }

    interface IPresenter{
        void getTypes();

        void getStates();

        void getJars();

        void getStatistic();
    }
}
