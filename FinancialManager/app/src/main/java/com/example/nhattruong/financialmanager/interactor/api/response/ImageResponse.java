package com.example.nhattruong.financialmanager.interactor.api.response;

import com.example.nhattruong.financialmanager.model.Image;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    private Image image;

    public Image getImage() {
        return image;
    }
}
