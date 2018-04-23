package com.example.nhattruong.financialmanager.interactor.api.request;

import com.example.nhattruong.financialmanager.model.Debt;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CreateDebtRequest {
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

    private CreateDebtRequest(Builder builder){
        this.date = builder.date;
        this.detail = builder.detail;
        this.amount = builder.amount;
        this.origin = builder.origin;
        this.state = builder.state;
        this.isPositive = builder.isPositive;
    }

    public static class Builder {
        private Date date;
        private String detail;
        private double amount;
        private String origin;
        private String state;
        private boolean isPositive;

        public Builder setDate(Date date){
            this.date = date;
            return this;
        }

        public Builder setDetail(String detail){
            this.detail = detail;
            return this;
        }

        public Builder setAmount(double amount){
            this.amount = amount;
            return this;
        }

        public Builder setOrigin(String origin){
            this.origin = origin;
            return this;
        }

        public Builder setState(String state){
            this.state = state;
            return this;
        }

        public Builder setPositive(boolean isPositive){
            this.isPositive = isPositive;
            return this;
        }

        public CreateDebtRequest build(){
            return new CreateDebtRequest(this);
        }
    }
}
