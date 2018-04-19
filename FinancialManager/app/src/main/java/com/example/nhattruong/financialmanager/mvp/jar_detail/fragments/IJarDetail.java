package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments;

public interface IJarDetail {
    String getDate();

    double getAmount();

    String getDetail();

    String getOrigin();

    String getState();

    boolean isPositive();
}
