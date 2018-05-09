package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Todo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TodoResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    private List<Todo> list;

    public List<Todo> getList() {
        if (list == null){
            list = new ArrayList<>();
        }
        return list;
    }
}
