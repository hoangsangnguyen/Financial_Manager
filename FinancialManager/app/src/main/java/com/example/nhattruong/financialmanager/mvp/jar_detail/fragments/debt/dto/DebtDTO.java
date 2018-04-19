package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.dto;

import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

public class DebtDTO implements IJarDetail {

    private DebtDTO dto;

    public DebtDTO(DebtDTO dto) {
        this.dto = dto;
    }

    @Override
    public String getDate() {
        return dto.getDate();
    }

    @Override
    public double getAmount() {
        return dto.getAmount();
    }

    @Override
    public String getDetail() {
        return dto.getDetail();
    }

    @Override
    public String getOrigin() {
        return dto.getOrigin();
    }

    @Override
    public String getState() {
        return dto.getState();
    }

    @Override
    public boolean isPositive() {
        return dto.isPositive();
    }
}
