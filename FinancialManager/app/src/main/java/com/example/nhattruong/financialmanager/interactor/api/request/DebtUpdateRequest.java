package com.example.nhattruong.financialmanager.interactor.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DebtUpdateRequest {
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

    @SerializedName("isPositve")
    @Expose
    private boolean isPositive;

    private DebtUpdateRequest(DebtUpdateRequestBuilder builder){
        this.date = builder.date;
        this.detail = builder.detail;
        this.amount = builder.amount;
        this.origin = builder.origin;
        this.state = builder.state;
        this.isPositive = builder.isPositive;
    }

    public static class DebtUpdateRequestBuilder{
        private Date date;
        private String detail;
        private double amount;
        private String origin;
        private String state;
        private boolean isPositive;

        public DebtUpdateRequestBuilder setDate(Date date){
            this.date = date;
            return this;
        }

        public DebtUpdateRequestBuilder setDetail(String detail){
            this.detail = detail;
            return this;
        }

        public DebtUpdateRequestBuilder setAmount(double amount){
            this.amount = amount;
            return this;
        }

        public DebtUpdateRequestBuilder setOrigin(String origin){
            this.origin = origin;
            return this;
        }

        public DebtUpdateRequestBuilder setState(String state){
            this.state = state;
            return this;
        }

        public DebtUpdateRequestBuilder setPositive(boolean isPositive){
            this.isPositive = isPositive;
            return this;
        }

        public DebtUpdateRequest build(){
            return new DebtUpdateRequest(this);
        }
    }
}
