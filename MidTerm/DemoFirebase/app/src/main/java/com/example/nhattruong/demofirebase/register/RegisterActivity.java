package com.example.nhattruong.demofirebase.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nhattruong.demofirebase.R;
import com.example.nhattruong.demofirebase.info.InfoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout mDisplayName;
    TextInputLayout mEmail;
    TextInputLayout mPassword;
    TextInputLayout mStudentCode;
    TextInputLayout mClassCode;
    Button mBtnCreate;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mDisplayName = findViewById(R.id.til_display_name);
        mEmail = findViewById(R.id.til_email);
        mPassword = findViewById(R.id.til_password);
        mBtnCreate = findViewById(R.id.btn_create_account);
        mStudentCode = findViewById(R.id.til_student_code);
        mClassCode = findViewById(R.id.til_class_code);

        progressDialog = new ProgressDialog(this);

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayName = mDisplayName.getEditText().getText().toString();
                String email = mEmail.getEditText().getText().toString();
                String password = mPassword.getEditText().getText().toString();
                String studentCode = mStudentCode.getEditText().getText().toString();
                String classCode = mClassCode.getEditText().getText().toString();

                progressDialog.setTitle("Register user");
                progressDialog.setMessage("Please wait when creating user");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                register(displayName, studentCode, classCode, email, password);
            }
        });
    }

    private void register(final String displayName, final String studentCode, final String classCode, String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    String uid = currentUser.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", displayName);
                    userMap.put("classCode", classCode);
                    userMap.put("studentCode", studentCode);

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(RegisterActivity.this, InfoActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
