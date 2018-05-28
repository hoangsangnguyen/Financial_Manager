package com.example.nhattruong.demofirebase.change_info;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nhattruong.demofirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChangeInfoActivity extends AppCompatActivity {

    TextInputLayout mName;
    TextInputLayout mStudentCode;
    TextInputLayout mClassCode;
    Button btnSave;
    Toolbar toolbar;

    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        mProgress = new ProgressDialog(this);

        mName = findViewById(R.id.til_display_name);
        mStudentCode = findViewById(R.id.til_student_code);
        mClassCode = findViewById(R.id.til_class_code);

        toolbar = findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change Info");

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mCurrentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        findViewById(R.id.btn_save_changes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mName.getEditText().getText().toString();
                String studentCode = mStudentCode.getEditText().getText().toString();
                String classCode = mClassCode.getEditText().getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(studentCode) && !TextUtils.isEmpty(classCode)){
                    mProgress.setTitle("Save changes");
                    mProgress.setMessage("Please wait while changing info user...");
                    mProgress.setCanceledOnTouchOutside(false);
                    mProgress.show();

                    HashMap<String, Object> newInfoMap = new HashMap<>();
                    newInfoMap.put("name", name);
                    newInfoMap.put("studentCode", studentCode);
                    newInfoMap.put("classCode", classCode);

                    mDatabase.updateChildren(newInfoMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mProgress.dismiss();
                            } else {
                                mProgress.hide();
                                Toast.makeText(ChangeInfoActivity.this, "Upate error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(ChangeInfoActivity.this, "Please fill full info!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
