package com.example.nhattruong.financialmanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("userID")
    @Expose
    private String userID;

    @Expose(serialize = false, deserialize = false)
    private boolean isNotified;

    public Todo() {
    }

    public Todo(String name, Date date) {
        this.id = "";
        this.name = name;
        this.date = date;
        this.userID = "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getUserID() {
        return userID;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }
}
