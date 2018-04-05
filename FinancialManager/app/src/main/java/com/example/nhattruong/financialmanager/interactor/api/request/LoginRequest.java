package com.example.nhattruong.financialmanager.interactor.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("userName")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
