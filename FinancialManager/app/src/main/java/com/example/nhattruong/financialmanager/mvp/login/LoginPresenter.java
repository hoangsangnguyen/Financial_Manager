package com.example.nhattruong.financialmanager.mvp.login;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.LoginRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;

import butterknife.Unbinder;

class LoginPresenter extends BasePresenter implements LoginContract.IPresenter{

    @Override
    public LoginContract.View getView() {
        return (LoginContract.View) super.getView();
    }

    @Override
    public void onCreate(IBaseView view, Unbinder binder) {
        super.onCreate(view, binder);
    }

    @Override
    public void login(String username, String password) {
        getView().showLoading();
        getApiManager().login(new LoginRequest(username, password), new ApiCallback<UserResponse>() {
            @Override
            public void success(UserResponse res) {
                if (res.result != null) {
                    getPreferManager().setUser(res.result);
                    getPreferManager().setToken(res.result.getToken());
                }

                getView().hideLoading();
                getView().onSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().hideLoading();
                getView().onFailure(error.toString());
            }
        });
    }
}
