package com.example.nhattruong.financialmanager.mvp.chart;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.StatisticRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.StatisticResponse;
import com.example.nhattruong.financialmanager.utils.DateUtils;

public class ChartPresenter extends BasePresenter implements ChartContract.Presenter{

    @Override
    public ChartContract.View getView() {
        return (ChartContract.View)super.getView();
    }

    @Override
    public void getStatistic() {
        if (!isViewAttached()) return;
        getView().showLoading();

        final String startDayMonth = "-1-1";
        String startDate = DateUtils.getCurrentYear() + startDayMonth;
        String endDate = (DateUtils.getCurrentYear() + 1) + startDayMonth;
        getApiManager().getStatistic(getSQLiteManager().getUser().getId(), new StatisticRequest(startDate, endDate), new ApiCallback<StatisticResponse>() {
            @Override
            public void success(StatisticResponse res) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().onSuccess(res);
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().onFailure(error);
            }
        });
    }
}
