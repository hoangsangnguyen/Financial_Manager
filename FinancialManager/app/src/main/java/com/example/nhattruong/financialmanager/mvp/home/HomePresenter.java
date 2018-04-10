package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.model.Jar;
import com.example.nhattruong.financialmanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BasePresenter implements HomeContract.IPresenter {
    @Override
    public HomeContract.View getView() {
        return (HomeContract.View) super.getView();
    }

    private List<Jar> jarList = new ArrayList<>();

    List<Jar> getJarList() {
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
        User user = getPreferManager().getUser();
        if (user != null) {
            getApiManager().getJars(user.getId(), new ApiCallback<JarResponse>() {
                @Override
                public void success(JarResponse res) {
                    if (!isViewAttached()) return;
                    getView().hideLoading();
                    if (res.result != null) {
                        jarList = res.result;
                        getView().onLoadJarsSuccess();
                    }
                }

                @Override
                public void failure(RestError error) {
                    if (!isViewAttached()) return;
                    getView().hideLoading();
                    getView().onLoadJarsFailed();
                }
            });
        } else {
            if (!isViewAttached()) return;
            getView().hideLoading();
        }
    }
}
