package com.example.nhattruong.financialmanager.mvp.profile;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.ImageResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    @Override
    public void uploadImage(MultipartBody.Part filePart) {
        if (!isViewAttached()) return;
        getView().showLoading();

        getApiManager().uploadImage(getSQLiteManager().getUser().getId(),filePart, new ApiCallback<ImageResponse>() {
            @Override
            public void success(ImageResponse res) {
                getUser().setAvatarUrl(res.getImage().getUrl());
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().onUploadImageSuccess(res.getImage().getUrl());
            }

            @Override
            public void failure(RestError error) {
               if (!isViewAttached()) return;
               getView().hideLoading();
               getView().getUserFailed(error);
            }
        });
    }

    @Override
    public void updateUser() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().updateUser(getSQLiteManager().getUser().getId(), getUser(), new ApiCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse res) {
                if (!isViewAttached()) return;
                getView().hideLoading();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().updateUserFailed(error);
            }
        });
    }
}
