package com.example.nhattruong.financialmanager.mvp.detail;

import com.example.nhattruong.financialmanager.base.IBaseView;

/**
 * Created by Administrator on 19/03/2018.
 */

public interface DetailContract {
    interface View extends IBaseView {
        void onLoadJarSuccess();
        void onLoadJarFailed();
    }

    interface IPresenter{

    }
}
