package com.example.nhattruong.financialmanager.mvp.signup;

import android.os.Bundle;
import android.widget.EditText;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;

import butterknife.BindView;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    @BindView(R.id.edt_first_name)
    EditText edtFirstName;

    @BindView(R.id.edt_last_name)
    EditText edtLastName;

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

    }
}
