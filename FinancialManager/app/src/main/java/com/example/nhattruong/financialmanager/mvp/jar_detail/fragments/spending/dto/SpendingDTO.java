package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending.dto;

import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.util.Date;

public class SpendingDTO implements IJarDetail{

    private Spending obj;

    public SpendingDTO(Spending obj) {
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
