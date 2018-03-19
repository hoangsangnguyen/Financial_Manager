package com.example.nhattruong.financialmanager.mvp.login.detail;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.model.Detail;

import java.util.List;

import butterknife.Unbinder;

public class DetailPresenter extends BasePresenter implements DetailContract.IPresenter {

    @Override
    public void onCreate(IBaseView view, Unbinder binder) {
        super.onCreate(view, binder);
    }

    @Override
    public void detail(String title, float thu, float chi) {
    }
}
