package com.example.nhattruong.financialmanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class  Income {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("amount")
    @Expose
    private double amount;

    public Income(String id, Date date, String detail, double amount) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
