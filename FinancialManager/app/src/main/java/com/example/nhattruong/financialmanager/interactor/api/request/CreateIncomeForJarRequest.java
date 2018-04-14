package com.example.nhattruong.financialmanager.interactor.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CreateIncomeForJarRequest {
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("amount")
    @Expose
    private int amount;

    private CreateIncomeForJarRequest(Builder builder) {
        this.date = builder.date;
        this.detail = builder.detail;
        this.amount = builder.amount;
    }

    public static class Builder{
        private Date date;
        private String detail;
        private int amount;

        public Builder setDate(Date date){
            this.date = date;
            return this;
        }

        public Builder setDetail(String detail){
            this.detail = detail;
            return this;
        }

        public Builder setAmount(int amount){
            this.amount = amount;
            return this;
        }

        public CreateIncomeForJarRequest build(){
           return new CreateIncomeForJarRequest(this);
        }
    }
}
