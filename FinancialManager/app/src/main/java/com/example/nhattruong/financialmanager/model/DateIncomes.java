package com.example.nhattruong.financialmanager.model;

import java.util.Date;
import java.util.List;

public class DateIncomes {

    private Date date;
    private List<Income> incomeList;

    public DateIncomes() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
}
