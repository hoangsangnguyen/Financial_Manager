package com.example.nhattruong.financialmanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 19/03/2018.
 */

public class Jar implements Serializable {
    @SerializedName("ID")
    @Expose
    private Integer id;

    @SerializedName("typeID")
    @Expose
    private Integer typeID;

    @SerializedName("inCome")
    @Expose
    private double inCome;

    @SerializedName("debt")
    @Expose
    private double debt;

    @SerializedName("spend")
    @Expose
    private double spend;

    @SerializedName("userID")
    @Expose
    private Integer userID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public double getInCome() {
        return inCome;
    }

    public void setInCome(double inCome) {
        this.inCome = inCome;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
