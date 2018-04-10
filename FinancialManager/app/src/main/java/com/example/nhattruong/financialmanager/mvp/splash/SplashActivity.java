package com.example.nhattruong.financialmanager.mvp.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.nhattruong.financialmanager.MainApplication;
import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.mvp.login.LoginActivity;
import com.example.nhattruong.financialmanager.mvp.setting.SettingActivity;

public class SplashActivity
        extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 1000;
    private boolean mExisted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mExisted) {
                    return;
                }
                if (MainApplication.getAppComponent().getPreferManager().isLogin()) {
                    Intent intent = new Intent(SplashActivity.this, SettingActivity.class);
                    intent.setData(getIntent().getData());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mExisted = true;
    }

    @Override
    public boolean isTransparentStatusBar() {
        return true;
    }
}
