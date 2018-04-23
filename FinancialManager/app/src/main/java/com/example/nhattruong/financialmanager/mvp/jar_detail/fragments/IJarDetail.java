package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments;

import android.support.annotation.NonNull;

import java.util.Date;

public interface IJarDetail {
    String getId();

    Date getDate();

    double getAmount();

    String getDetail();

    String getOrigin();

    String getState();

    boolean isPositive();

}
