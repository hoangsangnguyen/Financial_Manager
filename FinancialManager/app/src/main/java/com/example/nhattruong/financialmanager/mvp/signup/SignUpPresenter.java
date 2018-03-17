package com.example.nhattruong.financialmanager.mvp.signup;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;

public class SignUpPresenter extends BasePresenter implements SignUpContract.IPresenter {
    @Override
    public SignUpContract.View getView() {
        return (SignUpContract.View) super.getView();
    }

    @Override
    public void signUp(SignUpRequest request) {
        getView().showLoading();
        getApiManager().signUp(request, new ApiCallback<UserResponse>() {
            @Override
            public void success(UserResponse res) {
                getView().hideLoading();
                getView().onSuccess(res.result);
            }

            @Override
            public void failure(RestError error) {
                getView().hideLoading();
                getView().onFailure(error);
            }
        });
    }
}
