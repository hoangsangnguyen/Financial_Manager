package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.dto;

import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.util.Date;

public class DebtDTO implements IJarDetail {

    private Debt dto;

    public DebtDTO(Debt dto) {
        this.dto = dto;
    }

    @Override
    public String getId() {
        return dto.getId();
    }

    @Override
    public Date getDate() {
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

    /*public void setDebtChanged(Debt debt){
        dto = new Debt();
        dto.setId(debt.getId());
        dto.setDate(debt.getDate());
        dto.setDetail(debt.getDetail());
        dto.setAmount(debt.getAmount());
        dto.setOrigin(debt.getOrigin());
        dto.setState(debt.getState());
        dto.setPositive(debt.isPositive());
    }*/
}
