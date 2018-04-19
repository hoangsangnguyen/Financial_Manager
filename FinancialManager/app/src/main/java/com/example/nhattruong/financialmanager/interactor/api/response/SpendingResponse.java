package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Spending;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpendingResponse extends BaseResponse{

    @SerializedName("results")
    @Expose
    private List<Spending> mList;

    public List<Spending> getSpendings() {
        if (mList == null){
            mList = new ArrayList<>();
        }
        return mList;
    }
}
