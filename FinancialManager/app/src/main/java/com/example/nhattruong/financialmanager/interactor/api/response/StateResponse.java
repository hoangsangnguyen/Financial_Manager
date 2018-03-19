package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.State;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 19/03/2018.
 */

public class StateResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    public List<State> result;
}
