package com.example.nhattruong.financialmanager.mvp.login.detail;

import com.example.nhattruong.financialmanager.base.IBaseView;

public interface DetailContract {

    interface View extends IBaseView {
        void onSuccess();

        void onFailure(String error);
    }

    interface IPresenter {
        void detail(String title, float thu, float chi);
    }
}
