package com.example.nhattruong.financialmanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Debt {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("amount")
    @Expose
    private double amount;

    @SerializedName("origin")
    @Expose
    private String origin;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("isPositive")
    @Expose
    private boolean isPositive;

    public Debt() {
    }

    public Debt(String id, String date, String detail, double amount) {
        this.id = id;
        this.date = date;
        this.detail = detail;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrigin() {
        return origin;
    }

    public String getState() {
        return state;
    }

    public boolean isPositive() {
        return isPositive;
    }
}
