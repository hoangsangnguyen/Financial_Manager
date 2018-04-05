package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Jar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JarResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    public List<Jar> result;
}
