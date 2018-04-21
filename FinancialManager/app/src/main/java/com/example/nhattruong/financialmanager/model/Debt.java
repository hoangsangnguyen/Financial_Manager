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
    private Date date;
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

    public Debt(String id, Date date, String detail, double amount) {
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

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
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

    private Debt(DebtBuilder builder){
        this.id = builder.id;
        this.date = builder.date;
        this.detail = builder.detail;
        this.amount = builder.amount;
        this.origin = builder.origin;
        this.state = builder.state;
        this.isPositive = builder.isPositive;
    }

    public static class DebtBuilder {
        private String id;
        private Date date;
        private String detail;
        private double amount;
        private String origin;
        private String state;
        private boolean isPositive;

        public DebtBuilder setId(String id){
            this.id = id;
            return this;
        }

        public DebtBuilder setDate(Date date){
            this.date = date;
            return this;
        }

        public DebtBuilder setDetail(String detail){
            this.detail = detail;
            return this;
        }

        public DebtBuilder setAmount(double amount){
            this.amount = amount;
            return this;
        }

        public DebtBuilder setOrigin(String origin){
            this.origin = origin;
            return this;
        }

        public DebtBuilder setState(String state){
            this.state = state;
            return this;
        }

        public DebtBuilder setPositive(boolean isPositive){
            this.isPositive = isPositive;
            return this;
        }

        public Debt build(){
            return new Debt(this);
        }
    }
}
