package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income.dto;


import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.util.Date;

public class IncomeDTO implements IJarDetail {

    private Income obj;

    public IncomeDTO(Income obj) {
        this.obj = obj;
    }

    @Override
    public String getId() {
        return obj.getId();
    }

    @Override
    public Date getDate() {
        return obj.getDate();
    }

    @Override
    public double getAmount() {
        return obj.getAmount();
    }

    @Override
    public String getDetail() {
        return obj.getDetail();
    }

    @Override
    public String getOrigin() {
        return null;
    }

    @Override
    public String getState() {
        return null;
    }

    @Override
    public boolean isPositive() {
        return false;
    }
}
