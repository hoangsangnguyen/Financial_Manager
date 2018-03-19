package com.example.nhattruong.financialmanager.mvp.home;

import android.util.Log;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.StateResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.TypeResponse;
import com.example.nhattruong.financialmanager.model.Jar;
import com.example.nhattruong.financialmanager.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BasePresenter implements HomeContract.IPresenter {
    @Override
    public HomeContract.View getView() {
        return (HomeContract.View) super.getView();
    }

    private List<Jar> jarList = new ArrayList<>();

    public List<Jar> getJarList() {
        return jarList;
    }

    @Override
    public void getTypes() {
        getView().showLoading();
        getApiManager().getTypes(new ApiCallback<TypeResponse>() {
            @Override
            public void success(TypeResponse res) {
                if (res != null) {
                    Log.d("TYPES : ", res.result.size() + "\n" + new Gson().toJson(res.result));
                    getStates();
                } else {
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
    public void getStates() {
//        getView().showLoading();
        getApiManager().getStates(new ApiCallback<StateResponse>() {
            @Override
            public void success(StateResponse res) {
                if (res != null) {
                    Log.d("STATES : ", res.result.size() + "\n" + new Gson().toJson(res.result));
                    getJars();
                } else {
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
//        getView().showLoading();
        User user = getPreferManager().getUser();
        if (user != null) {
            String userName = user.getUserName();
            String token = getPreferManager().getToken();
            getApiManager().getJars(token, userName, new ApiCallback<JarResponse>() {
                @Override
                public void success(JarResponse res) {
                    Log.d("JARS : ", res.result.size() + " : " + new Gson().toJson(res.result));
                    getView().hideLoading();
                    if (res.result != null) {
                        jarList = res.result;
                        getView().onLoadJarsSuccess();
                    }
                }

                @Override
                public void failure(RestError error) {
                    getView().hideLoading();
                    getView().onLoadJarsFailed();
                }
            });
        } else {
            getView().hideLoading();
        }
    }
}
