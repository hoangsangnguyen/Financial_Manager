package com.example.nhattruong.financialmanager.mvp.login;

import com.example.nhattruong.financialmanager.base.IBaseView;

public interface LoginContract {
    interface View extends IBaseView{
        void onSuccess();

        void onFailure(String error);
    }

    interface IPresenter{
        void login(String username, String password);
    }
}
