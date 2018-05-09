package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Todo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTodoResponse extends BaseResponse{
    @SerializedName("results")
    @Expose
    private Todo todo;

    public Todo getTodo(){
        return todo;
    }
}
