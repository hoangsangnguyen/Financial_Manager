package com.example.nhattruong.financialmanager.mvp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.custom_view.CustomEditText;
import com.example.nhattruong.financialmanager.dialog.DialogOk;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.edt_username)
    CustomEditText edtUsername;

    @BindView(R.id.edt_password)
    CustomEditText edtPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_sign_up)
    Button btnSignUp;

    @Override
    public boolean isTransparentStatusBar() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new LoginPresenter());
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInitData() {
    }

    @Override
    public void onInitListener() {
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public LoginPresenter getPresenter() {
        return (LoginPresenter) super.getPresenter();
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            getPresenter().login(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());
        }
    }

    @Override
    public void onSuccess() {
        showOkDialog(getString(R.string.login), getString(R.string.success), null);
    }

    @Override
    public void onFailure(String error) {
        showOkDialog(getString(R.string.login), getString(R.string.failed), new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
//                edtUsername.requestFocus();
                dialog.dismiss();
            }
        });
    }
}
