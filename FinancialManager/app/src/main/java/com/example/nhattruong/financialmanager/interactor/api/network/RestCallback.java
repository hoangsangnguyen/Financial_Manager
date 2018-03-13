package com.example.nhattruong.financialmanager.interactor.api.network;


import android.util.Log;

import com.example.nhattruong.financialmanager.MainApplication;
import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RestCallback<T extends BaseResponse> implements Callback<T> {

    public abstract void success(T res);

    public abstract void failure(RestError error);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.d("Response", response.message());

        if (response.isSuccessful()) {
            T bodyResponse = response.body();
            if (bodyResponse.success) {
                success(bodyResponse);
            } else {
                RestError error = new RestError(0);
                if (bodyResponse.message != null && !bodyResponse.message.equals("")) {
                    error = new RestError(bodyResponse.status, bodyResponse.message);
                } else if (bodyResponse.error != null && !bodyResponse.error.equals("")) {
                    error = new RestError(bodyResponse.status, bodyResponse.error);
                }
                failure(error);
            }
        } else {
            RestError error = new RestError(APIErrorUtils.API_ERROR_UNKNOWN, MainApplication.getAppComponent().getContext().getResources().getString(R.string.api_err_unknown_error));
            failure(error);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        Log.e("OnFailure", throwable.toString());

        RestError error = null;
        if (throwable instanceof IOException) {
            if (throwable instanceof SocketTimeoutException) {
                error = new RestError(APIErrorUtils.API_ERROR_TIMED_OUT, MainApplication.getAppComponent().getContext().getResources().getString(R.string.api_err_time_out));
            } else {
                error = new RestError(APIErrorUtils.API_ERROR_NO_NETWORK, MainApplication.getAppComponent().getContext().getResources().getString(R.string.no_internet_network));
            }
        }
        if (error == null) {
            error = new RestError(APIErrorUtils.API_ERROR_UNKNOWN, MainApplication.getAppComponent().getContext().getResources().getString(R.string.api_err_unknown_error));
        }
        failure(error);
    }


}