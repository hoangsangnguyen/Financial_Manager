package com.example.nhattruong.financialmanager.mvp.overlap;

import com.example.nhattruong.financialmanager.base.IBaseView;

/**
 * Created by nhattruong on 4/11/2018.
 */

public interface CreateIncomeContract {
    interface View extends IBaseView{

        void getAllJarSuccess();
    }

    interface Presenter {
        void getAllJar();
    }
}
