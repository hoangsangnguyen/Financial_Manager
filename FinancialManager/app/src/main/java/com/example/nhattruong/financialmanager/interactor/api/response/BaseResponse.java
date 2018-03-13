package com.example.nhattruong.financialmanager.interactor.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("error")
    @Expose
    public String error;

}