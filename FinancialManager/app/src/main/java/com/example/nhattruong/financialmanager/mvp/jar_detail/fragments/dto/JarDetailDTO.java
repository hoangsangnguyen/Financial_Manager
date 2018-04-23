package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto;

import android.support.annotation.NonNull;

import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JarDetailDTO implements Comparable<JarDetailDTO>{
    private Date date;
    private List<IJarDetail> mList;

    public JarDetailDTO() {
    }

    public JarDetailDTO(Date date, List<IJarDetail> mList) {
        this.date = date;
        this.mList = mList;
    }

    public Date getDate() {
        return date;
    }

    public List<IJarDetail> getList() {
        if (mList == null){
            mList = new ArrayList<>();
        }
        return mList;
    }

    @Override
    public int compareTo(@NonNull JarDetailDTO jarDetailDTO) {
        return date.compareTo(jarDetailDTO.getDate());
    }
}
