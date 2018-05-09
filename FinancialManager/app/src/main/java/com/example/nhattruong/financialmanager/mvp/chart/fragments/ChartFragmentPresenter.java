package com.example.nhattruong.financialmanager.mvp.chart.fragments;

import com.example.nhattruong.financialmanager.base.BasePresenter;

public class ChartFragmentPresenter extends BasePresenter implements ChartFragmentContract.Presenter{

    private String mJarId;

    public void setJarId(String jarId) {
        this.mJarId = jarId;
    }

    @Override
    public ChartFragmentContract.View getView() {
        return (ChartFragmentContract.View)super.getView();
    }
}
