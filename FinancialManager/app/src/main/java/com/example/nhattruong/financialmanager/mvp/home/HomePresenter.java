package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;

/**
 * Created by nhattruong on 3/15/2018.
 */

public class HomePresenter extends BasePresenter implements HomeContract.IPresenter {
    @Override
    public HomeContract.View getView() {
        return (HomeContract.View) super.getView();
    }
}
