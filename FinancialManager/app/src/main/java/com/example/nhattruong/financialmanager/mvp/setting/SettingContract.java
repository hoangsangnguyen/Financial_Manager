package com.example.nhattruong.financialmanager.mvp.setting;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

public interface SettingContract {
    interface View extends IBaseView{
        void getUserInfoSuccess();

        void getUserInfoError(RestError error);
    }

    interface Presenter {
        void getUserInfo();

        void logout();
    }
}
