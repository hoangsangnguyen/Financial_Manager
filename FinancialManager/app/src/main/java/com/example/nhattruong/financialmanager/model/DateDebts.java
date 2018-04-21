package com.example.nhattruong.financialmanager.model;

import java.util.Date;
import java.util.List;

public class DateDebts {
    private Date date;
    private List<Debt> debtList;

    public DateDebts() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Debt> getDebtList() {
        return debtList;
    }

    public void setDebtList(List<Debt> debtList) {
        this.debtList = debtList;
    }
}
