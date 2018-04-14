package com.example.nhattruong.financialmanager.model;

import java.util.List;

public class DateDebts {
    private String date;
    private List<Debt> debtList;

    public DateDebts() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Debt> getDebtList() {
        return debtList;
    }

    public void setDebtList(List<Debt> debtList) {
        this.debtList = debtList;
    }
}
