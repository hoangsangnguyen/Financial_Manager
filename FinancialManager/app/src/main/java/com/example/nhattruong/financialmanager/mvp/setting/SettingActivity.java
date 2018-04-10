package com.example.nhattruong.financialmanager.mvp.setting;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.dialog.DialogPositiveNegative;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.mvp.login.LoginActivity;
import com.example.nhattruong.financialmanager.utils.DialogUtils;

import butterknife.BindView;

public class SettingActivity extends BaseActivity implements SettingContract.View, View.OnClickListener {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_logout)
    TextView tvLogout;

    @BindView(R.id.tv_retry)
    TextView tvRetry;

    private ObjectAnimator animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new SettingPresenter());
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);

        getPresenter().getUserInfo();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startProgress();
            }
        }, 300);
    }

    @Override
    public SettingPresenter getPresenter() {
        return (SettingPresenter) super.getPresenter();
    }

    @Override
    public void onInitData() {
    }

    @Override
    public void onInitListener() {
        tvLogout.setOnClickListener(this);
        tvRetry.setOnClickListener(this);
    }

    private void startProgress() {
        smoothProgress(80, 800, new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private void smoothProgress(int toProgress, int inMilliseconds, final Runnable endRunnable) {
        animation = ObjectAnimator.ofInt(progressBar, "progress", toProgress);
        animation.setDuration(inMilliseconds);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.removeAllListeners();
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                endRunnable.run();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }

    @Override
    public void getUserInfoSuccess() {
        smoothProgress(100, 200, new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SettingActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    @Override
    public void getUserInfoError(RestError error) {
        showError(true);
    }

    private void showError(boolean isShow) {
        if (isShow) {
            if (animation != null) {
                animation.removeAllListeners();
                animation.cancel();
            }
            progressBar.clearAnimation();
            progressBar.setProgress(0);
        }
        tvLogout.setVisibility(isShow ? View.VISIBLE : View.GONE);
        tvRetry.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == tvLogout) {
            showConfirmLogout();
        } else if (view == tvRetry) {
            restartLoadSetting();
        }
    }

    private void restartLoadSetting() {
        progressBar.setProgress(0);
        showError(false);
        getPresenter().getUserInfo();
        startProgress();
    }

    private void showConfirmLogout() {
        DialogUtils.showConfirmDialog(
                this,
                getString(R.string.app_name),
                getString(R.string.confirm_logout),
                new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        dialog.dismiss();
                        getPresenter().logout();
                        Intent loginIntent = new Intent(SettingActivity.this, LoginActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loginIntent);
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                        dialog.dismiss();
                    }
                });
    }


}
