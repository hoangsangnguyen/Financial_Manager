package com.example.nhattruong.financialmanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Jar implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("incomes")
    @Expose
    private double incomes;

    @SerializedName("debts")
    @Expose
    private double debts;

    @SerializedName("spendings")
    @Expose
    private double spendings;

    @SerializedName("availableAmount")
    @Expose
    private double availableAmout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getIncomes() {
        return incomes;
    }

    public void setIncomes(int incomes) {
        this.incomes = incomes;
    }

    public double getDebts() {
        return debts;
    }

    public void setDebts(int debts) {
        this.debts = debts;
    }

    public double getSpending() {
        return spendings;
    }

    public void setSpending(int spendings) {
        this.spendings = spendings;
    }

    public double getAvailableAmout() {
        return availableAmout;
    }

    public void setAvailableAmout(double availableAmout) {
        this.availableAmout = availableAmout;
    }
}
