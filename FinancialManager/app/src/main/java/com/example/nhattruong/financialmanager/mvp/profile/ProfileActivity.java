package com.example.nhattruong.financialmanager.mvp.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.edt_name)
    EditText edtName;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.btn_Save)
    Button btnSave;

    @BindView(R.id.btn_Logout)
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInitData() {
        edtEmail.setText("A");
    }

    @Override
    public void onInitListener() {
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick(R.id.btn_Save)
    public void onSave() {
        Toast.makeText(this,edtEmail.getText(),Toast.LENGTH_LONG).show();
    }
}
