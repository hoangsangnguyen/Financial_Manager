package com.example.nhattruong.financialmanager.model;

public class Category {
    private Integer image;
    private String label;

    public Category(String label, Integer image) {
        this.image = image;
        this.label = label;
    }

    public Integer getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }
}
