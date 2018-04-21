package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Debt;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDebtResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    private Debt debt;

    public Debt getDebt() {
        return debt;
    }
}
