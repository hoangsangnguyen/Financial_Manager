package com.example.nhattruong.financialmanager.mvp.home;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;

public interface HomeContract {

    interface View extends IBaseView{

        void onLoadJarsSuccess();

        void onLoadJarsFailed(RestError error);

    }

    interface IPresenter{
        void getTypes();

        void getStates();

        void getJars();
    }
}
