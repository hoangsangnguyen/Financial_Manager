package com.example.nhattruong.financialmanager.model;

import java.util.Date;
import java.util.List;

public class DateSpendings {

    private Date date;
    private List<Spending> spendingList;

    public DateSpendings() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Spending> getSpendingList() {
        return spendingList;
    }

    public void setSpendingList(List<Spending> spendingList) {
        this.spendingList = spendingList;
    }
}
