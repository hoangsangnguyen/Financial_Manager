package com.example.nhattruong.financialmanager.mvp.signup;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.model.User;

public interface SignUpContract {
    interface View extends IBaseView {
        void onSuccess(User user);

        void onFailure(RestError error);
    }

    interface IPresenter {
        void signUp(SignUpRequest request);
    }
}
