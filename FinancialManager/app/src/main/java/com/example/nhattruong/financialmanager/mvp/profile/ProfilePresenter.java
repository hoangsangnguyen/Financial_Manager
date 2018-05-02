package com.example.nhattruong.financialmanager.mvp.profile;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.model.User;

public class ProfilePresenter extends BasePresenter implements ProfileContract.Presenter{

    private User mUser;

    public User getUser() {
        if (mUser == null){
            mUser = new User();
        }
        return mUser;
    }

    @Override
    public ProfileContract.View getView() {
        return (ProfileContract.View)super.getView();
    }

    @Override
    public void getUserInfo() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().getUser(getSQLiteManager().getUser().getId(), new ApiCallback<UserResponse>() {
            @Override
            public void success(UserResponse res) {
                mUser = res.result;
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().getUserSuccess(res.result);
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().getUserFailed(error);
            }
        });
    }
}
