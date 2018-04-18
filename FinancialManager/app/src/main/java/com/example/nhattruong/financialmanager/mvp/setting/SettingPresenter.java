package com.example.nhattruong.financialmanager.mvp.setting;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.google.gson.Gson;

public class SettingPresenter extends BasePresenter implements SettingContract.Presenter {

    @Override
    public SettingContract.View getView() {
        return (SettingContract.View)super.getView();
    }

    @Override
    public void getUserInfo() {
        getApiManager().getUser(getPreferManager().getUser().getId(), new ApiCallback<UserResponse>() {
            @Override
            public void success(UserResponse res) {
                if (!isViewAttached()) return;
                getPreferManager().setUser(new Gson().toJson(res.result));
                getView().getUserInfoSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().getUserInfoError(error);
            }
        });
    }

    @Override
    public void logout() {

    }
}
