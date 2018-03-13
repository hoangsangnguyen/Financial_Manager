package com.example.nhattruong.financialmanager.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.example.nhattruong.financialmanager.R;

import butterknife.ButterKnife;

public abstract class BaseDialog extends Dialog {

    protected Context context;

    public BaseDialog(Context context) {
        super(context, R.style.FullscreenDialog);
        this.context = context;
        setStatusBarColorIfPossible(R.color.app_color);
    }

    public abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    private void setStatusBarColorIfPossible(int color) {
        Window window = getWindow();
        assert window != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getContext(), color));
        }
    }

}

