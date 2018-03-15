package com.example.nhattruong.financialmanager.mvp.signup;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;

public class SignUpPresenter extends BasePresenter implements SignUpContract.IPresenter{
    @Override
    public SignUpContract.View getView() {
        return (SignUpContract.View) super.getView();
    }
}
