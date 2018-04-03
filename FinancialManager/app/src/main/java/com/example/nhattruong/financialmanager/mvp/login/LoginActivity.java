package com.example.nhattruong.financialmanager.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.DialogOk;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.mvp.signup.SignUpActivity;
import com.example.nhattruong.financialmanager.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {

    public static final int REQUEST_CODE_SIGN_UP = 11;
    public static final String USER_NAME = "USER_NAME";
    public static final String IS_FIRST_LOGIN = "IS_FIRST_LOGIN";

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
        String username = getIntent().getStringExtra(USER_NAME);
        if (!TextUtils.isEmpty(username)) {
            edtUsername.setText(username);
        }
    }

    @Override
    public void onInitListener() {
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkValidEmail(edtUsername.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void checkValidEmail(String text) {
        if (!CommonUtils.isEmailValid(text)) {
            edtUsername.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
        edtUsername.setTextColor(
                CommonUtils.isEmailValid(text) ?
                        ContextCompat.getColor(this, R.color.app_color) : ContextCompat.getColor(this, R.color.red));
    }

    @Override
    public LoginPresenter getPresenter() {
        return (LoginPresenter) super.getPresenter();
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            getPresenter().login(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());
        } else if (view == btnSignUp) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SIGN_UP);
        }
    }

    @Override
    public void onSuccess() {

        Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intentHome);
        finish();
    }

    @Override
    public void onFailure(String error) {
        showErrorDialog(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SIGN_UP && resultCode == SignUpActivity.RESULT_CODE_SIGN_UP) {
            User userResponse = (User) data.getSerializableExtra(SignUpActivity.RESULT_SIGN_UP_DATA);
            edtUsername.setText(userResponse.getUserName());
            edtPassword.setText(userResponse.getPassword());
        }
    }
}
