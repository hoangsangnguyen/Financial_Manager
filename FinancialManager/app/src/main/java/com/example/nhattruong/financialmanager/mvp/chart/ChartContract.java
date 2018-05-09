package com.example.nhattruong.financialmanager.mvp.chart;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.StatisticResponse;

public interface ChartContract {
    interface View extends IBaseView{
        void onSuccess(StatisticResponse response);

        void onFailure(RestError restError);
    }

    interface Presenter {
        void getStatistic();
    }
}
