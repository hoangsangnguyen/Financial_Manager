package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.BasePresenter;

public class HomePresenter extends BasePresenter implements HomeContract.IPresenter {
    @Override
    public HomeContract.View getView() {
        return (HomeContract.View) super.getView();
    }
}
