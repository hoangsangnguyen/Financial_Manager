package com.example.nhattruong.financialmanager.mvp.profile;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.User;

public interface ProfileContract {
    interface View extends IBaseView{
        void getUserSuccess(User user);

        void getUserFailed(RestError error);
    }

    interface Presenter{
        void getUserInfo();
    }
}
