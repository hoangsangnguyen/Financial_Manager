package com.example.nhattruong.financialmanager.base;


public interface IBaseView {

    void showLoading();

    void hideLoading();

//    void onFail(RestError error);

    void showErrorNormal(String error);

}
