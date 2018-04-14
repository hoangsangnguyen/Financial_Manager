package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Debt;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DebtResponse extends BaseResponse{

    @SerializedName("results")
    @Expose
    private List<Debt> debts;

    public List<Debt> getDebts() {
        return debts;
    }
}
