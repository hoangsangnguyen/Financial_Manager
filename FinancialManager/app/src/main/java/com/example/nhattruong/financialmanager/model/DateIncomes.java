package com.example.nhattruong.financialmanager.model;

import java.util.Date;
import java.util.List;

public class DateIncomes {

    private String date;
    private List<Income> incomeList;

    public DateIncomes() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
}
