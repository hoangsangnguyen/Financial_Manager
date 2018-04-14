package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.IBaseView;

public interface HomeContract {

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
