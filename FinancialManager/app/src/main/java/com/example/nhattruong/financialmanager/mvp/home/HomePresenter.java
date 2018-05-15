package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.StatisticRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StatisticResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.model.Jar;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePresenter extends BasePresenter implements HomeContract.IPresenter {
    @Override
    public HomeContract.View getView() {
        return (HomeContract.View) super.getView();
    }

    private List<Jar> jarList;

    List<Jar> getJarList() {
        if (jarList == null) {
            jarList = new ArrayList<>();
        }
        return jarList;
    }

    @Override
    public void getTypes() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().getTypes(new ApiCallback<TypeResponse>() {
            @Override
            public void success(TypeResponse res) {
                if (res != null) {
                    getJars();
                } else {
                    if (!isViewAttached()) return;
                    getView().hideLoading();
                }
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
            }
        });
    }

    @Override
    public void getStates() {
        getApiManager().getStates(new ApiCallback<StateResponse>() {
            @Override
            public void success(StateResponse res) {
                if (res != null) {
                    getJars();
                } else {
                    if (!isViewAttached()) return;
                    getView().hideLoading();

                }
            }

            @Override
            public void failure(RestError error) {
                getView().hideLoading();
            }
        });
    }

    @Override
    public void getJars() {
        User user = getSQLiteManager().getUser();
        if (!isViewAttached()) return;
        getView().showLoading();
        if (user != null) {
            getApiManager().getJars(user.getId(), new ApiCallback<JarResponse>() {
                @Override
                public void success(JarResponse res) {
                    if (!isViewAttached()) return;
                    getView().hideLoading();
                    if (res.result != null) {
                        getJarList().clear();
                        jarList.addAll(res.result);
                        getView().onLoadJarsSuccess();
                    }
                }

                @Override
                public void failure(RestError error) {
                    if (!isViewAttached()) return;
                    getView().hideLoading();
                    getView().onFailure(error);
                }
            });
        } else {
            if (!isViewAttached()) return;
            getView().hideLoading();
        }
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
                getView().getStatisticSuccess(res);
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
