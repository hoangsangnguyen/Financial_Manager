package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Income;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IncomeResponse extends BaseResponse {

    @SerializedName("results")
    @Expose
    private List<Income> incomes;

    public List<Income> getIncomes() {
        return incomes;
    }
}
