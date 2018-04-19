package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto;

import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.util.Date;
import java.util.List;

public class JarDetailDTO {
    private String date;
    private List<IJarDetail> mList;

    public JarDetailDTO() {
    }

    public JarDetailDTO(String date, List<IJarDetail> mList) {
        this.date = date;
        this.mList = mList;
    }

    public String getDate() {
        return date;
    }

    public List<IJarDetail> getList() {
        return mList;
    }
}
