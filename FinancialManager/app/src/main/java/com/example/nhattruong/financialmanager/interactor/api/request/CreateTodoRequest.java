package com.example.nhattruong.financialmanager.interactor.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTodoRequest {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("date")
    @Expose
    private String date;

    public CreateTodoRequest(String name, String date) {
        this.name = name;
        this.date = date;
    }
}
