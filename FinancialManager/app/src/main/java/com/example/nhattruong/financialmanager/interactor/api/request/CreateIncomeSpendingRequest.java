package com.example.nhattruong.financialmanager.interactor.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CreateIncomeSpendingRequest {
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("amount")
    @Expose
    private double amount;

    private CreateIncomeSpendingRequest(Builder builder) {
        this.date = builder.date;
        this.detail = builder.detail;
        this.amount = builder.amount;
    }

    public static class Builder{
        private Date date;
        private String detail;
        private double amount;

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

        public CreateIncomeSpendingRequest build(){
           return new CreateIncomeSpendingRequest(this);
        }
    }
}
