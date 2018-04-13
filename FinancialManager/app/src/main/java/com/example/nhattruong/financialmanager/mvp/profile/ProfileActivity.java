package com.example.nhattruong.financialmanager.mvp.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.User;

import butterknife.BindView;

public class ProfileActivity extends BaseActivity implements ProfileContract.View, View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.tv_save)
    TextView tvSave;

    @BindView(R.id.edt_first_name)
    EditText edtFirstName;

    @BindView(R.id.edt_last_name)
    EditText edtLastName;

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.edt_phone)
    EditText edtPhone;

    @Override
    public ProfilePresenter getPresenter() {
        return (ProfilePresenter)super.getPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new ProfilePresenter());
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        getPresenter().getUserInfo();
    }

    @Override
    public void onInitData() {

    }

    @Override
    public void onInitListener() {
        ivBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    @Override
    public void getUserSuccess(User user) {
        edtFirstName.setText(user.getFirstName());
        edtLastName.setText(user.getLastName());
        edtUsername.setText(user.getUserName());
        edtEmail.setText(user.getEmail());
        edtPhone.setText(user.getPhone());
    }

    @Override
    public void getUserFailed(RestError error) {
        showRestErrorDialog(error, null);
    }

    @Override
    public void onClick(View view) {
        if (view == ivBack){
            onBackPressed();
        } else if (view == tvSave){
            //save user
        }
    }
}
