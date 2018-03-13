package com.example.nhattruong.financialmanager.interactor.api.network;

import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;

public abstract class ApiCallback<T extends BaseResponse> {

    public abstract void success(T res);

    public abstract void failure(RestError error);

}