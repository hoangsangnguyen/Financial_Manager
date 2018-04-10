package com.example.nhattruong.financialmanager.mvp.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.DialogOk;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.SignUpRequest;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.mvp.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    public final static int RESULT_CODE_SIGN_UP = 33;
    public final static String RESULT_SIGN_UP_DATA = "RESULT_SIGN_UP_DATA";

    @BindView(R.id.edt_first_name)
    TextInputLayout edtFirstName;

    @BindView(R.id.edt_last_name)
    TextInputLayout edtLastName;

    @BindView(R.id.edt_email)
    TextInputLayout edtEmail;

    @BindView(R.id.edt_phone)
    TextInputLayout edtPhone;

    @BindView(R.id.edt_username)
    TextInputLayout edtUsername;

    @BindView(R.id.edt_password)
    TextInputLayout edtPassword;

    /*@BindView(R.id.edt_confirm_password)
    TextInputLayout edtConfirmPassword;

    @BindView(R.id.iv_tick)
    ImageView ivTick;*/

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
        /*edtConfirmPassword.addTextChangedListener(new TextWatcher() {
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
        });*/
    }

    @OnClick(R.id.tv_sign_up)
    public void signUp() {
        if (missingInfo()) {
            showOkDialog("", "Please, fill full information!", null);
        } else {
            getPresenter().signUp(getInfoSignUp());
        }
    }

    @OnClick(R.id.iv_back)
    public void onBack() {
        onBackPressed();
    }

    private SignUpRequest getInfoSignUp() {
        String firstName = "";
        String lastName= "";
        String email= "";
        String phone = "";
        String password = "";
        String username = "";
        if (edtFirstName.getEditText() != null){
           firstName =  edtFirstName.getEditText().getText().toString();
        }
        if (edtLastName.getEditText() != null){
            lastName = edtLastName.getEditText().getText().toString();
        }
        if (edtEmail.getEditText() != null){
            email = edtEmail.getEditText().getText().toString();
        }
        if (edtPhone.getEditText()!=null){
            phone = edtPhone.getEditText().getText().toString();
        }
        if (edtUsername.getEditText()!= null){
            username = edtUsername.getEditText().getText().toString();
        }
        if (edtPassword.getEditText()!= null){
            password = edtPassword.getEditText().getText().toString();
        }

        return new SignUpRequest.SignUpRequestBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhone(phone)
                .setUserName(username)
                .setPassword(password)
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

    private boolean isTextEmpty(TextInputLayout textInputLayout) {
        return textInputLayout.getEditText() != null && textInputLayout.getEditText().getText().toString().trim().isEmpty();
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
