package com.example.nhattruong.financialmanager.mvp.home;

import android.os.Bundle;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new HomePresenter());
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    public HomePresenter getPresenter() {
        return (HomePresenter) super.getPresenter();
    }

    @Override
    public void onInitData() {

    }

    @Override
    public void onInitListener() {

    }
}
