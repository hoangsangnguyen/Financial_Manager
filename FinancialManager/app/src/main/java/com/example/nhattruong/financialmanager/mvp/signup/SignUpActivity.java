package com.example.nhattruong.financialmanager.mvp.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.DialogOk;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.UserResponse;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.mvp.login.LoginActivity;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    public final static int RESULT_CODE_SIGN_UP = 33;
    public final static String RESULT_SIGN_UP_DATA = "RESULT_SIGN_UP_DATA";

    @BindView(R.id.edt_first_name)
    EditText edtFirstName;

    @BindView(R.id.edt_last_name)
    EditText edtLastName;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.edt_phone)
    EditText edtPhone;

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;

    @BindView(R.id.iv_tick)
    ImageView ivTick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new SignUpPresenter());
        setContentView(R.layout.activity_sign_up);
        super.onCreate(savedInstanceState);
    }

    @Override
    public SignUpPresenter getPresenter() {
        return (SignUpPresenter) super.getPresenter();
    }

    @Override
    public void onInitData() {

    }

    @Override
    public void onInitListener() {
        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ivTick.setVisibility(
                        edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString()) ? View.VISIBLE : View.GONE
                );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp() {
        if (missingInfo()){
            showOkDialog("", "Vui long dien day du thong tin!", null);
        } else {
            if (ivTick.getVisibility() == View.GONE){
                showOkDialog("", "Xac nhan mat khau chua khop!", null);
            } else {
                getPresenter().signUp(getInfoSignUp());
            }
        }
    }

    @OnClick(R.id.iv_back)
    public void onBack(){
        onBackPressed();
    }

    private SignUpRequest getInfoSignUp(){
        return new SignUpRequest.SignUpRequestBuilder()
                .setFirstName(edtFirstName.getText().toString())
                .setLastName(edtLastName.getText().toString())
                .setEmail(edtEmail.getText().toString())
                .setPhone(edtPhone.getText().toString())
                .setUserName(edtUsername.getText().toString())
                .setPassword(edtPassword.getText().toString())
                .build();
    }

    private boolean missingInfo() {
        return isTextEmpty(edtFirstName)
                && isTextEmpty(edtLastName)
                && isTextEmpty(edtEmail)
                && isTextEmpty(edtPhone)
                && isTextEmpty(edtUsername)
                && isTextEmpty(edtPassword);
    }

    private boolean isTextEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    @Override
    public void onSuccess(final User user) {
        showOkDialog(getString(R.string.sign_up), "Success!", new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra(RESULT_SIGN_UP_DATA, user);
                setResult(RESULT_CODE_SIGN_UP, intent);
                finish();
            }
        });
    }

    @Override
    public void onFailure(RestError error) {
        showErrorDialog(error.message);
    }
}
