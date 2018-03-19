package com.example.nhattruong.financialmanager.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.DialogOk;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.mvp.signup.SignUpActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {

    public static final int REQUEST_CODE_SIGN_UP = 11;

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_password)
    EditText edtPassword;

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
        } else if (view == btnSignUp){
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SIGN_UP);
        }
    }

    @Override
    public void onSuccess() {
        showOkDialog(getString(R.string.login), getString(R.string.success), new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));

            }
        });
    }

    @Override
    public void onFailure(String error) {
        showErrorDialog(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SIGN_UP && resultCode == SignUpActivity.RESULT_CODE_SIGN_UP){
            User userResponse = (User) data.getSerializableExtra(SignUpActivity.RESULT_SIGN_UP_DATA);
            edtUsername.setText(userResponse.getUserName());
            edtPassword.setText(userResponse.getPassword());
        }
    }
}
