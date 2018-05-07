package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto;

import android.support.annotation.NonNull;

import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JarDetailDTO implements Comparable<JarDetailDTO>{
    private Date date;
    private List<IJarDetail> mList;

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
        SimpleDateFormat math = new SimpleDateFormat("yyyyMMdd");
        Long date1asLong = new Long(math.format(date));
        Long date2asLong = new Long(math.format(jarDetailDTO.date));
        return date1asLong.compareTo(date2asLong);
    }
}
