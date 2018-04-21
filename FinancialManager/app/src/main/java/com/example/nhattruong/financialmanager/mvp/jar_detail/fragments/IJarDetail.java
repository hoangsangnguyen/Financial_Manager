package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments;

public interface IJarDetail {
    String getId();

    String getDate();

    double getAmount();

    String getDetail();

    String getOrigin();

    String getState();

    boolean isPositive();
}
