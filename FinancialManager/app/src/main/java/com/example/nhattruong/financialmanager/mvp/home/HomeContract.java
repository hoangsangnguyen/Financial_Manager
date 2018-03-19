package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.model.Jar;

import java.util.List;

public interface HomeContract {
    int STATE_DONE = 1;
    int STATE_READY = 2;
    int STATE_WAITTING = 3;

    int TYPE_NHU_CAU_THIET_YEU = 1;
    int TYPE_TIET_KIEM_DAI_HAN = 2;
    int TYPE_GIAO_DUC = 3;
    int TYPE_THU_HUONG = 4;
    int TYPE_CHO_DI = 5;
    int TYPE_TU_DO_TAI_CHINH = 6;
    int TYPE_TONG_HOP = 7;

    interface View extends IBaseView{
        void onLoadJarsSuccess();
        void onLoadJarsFailed();

    }

    interface IPresenter{
        void getTypes();
        void getStates();
        void getJars();
    }
}
