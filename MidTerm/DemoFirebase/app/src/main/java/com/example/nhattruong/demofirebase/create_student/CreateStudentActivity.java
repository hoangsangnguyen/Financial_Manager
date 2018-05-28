package com.example.nhattruong.demofirebase.create_student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhattruong.demofirebase.R;
import com.example.nhattruong.demofirebase.create_student.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateStudentActivity extends AppCompatActivity {

    TextInputLayout edtName, edtStudentCode;
    TextView tvTitle;

    private DatabaseReference mDatabase;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mProgress = new ProgressDialog(this);

        tvTitle = findViewById(R.id.tv_title_top_bar);
        tvTitle.setText(R.string.create_student);

        edtName = findViewById(R.id.edt_name);
        edtStudentCode = findViewById(R.id.edt_student_code);
        findViewById(R.id.tv_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStudent();
            }
        });

        findViewById(R.id.tv_show_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateStudentActivity.this, ListStudentActivity.class));
            }
        });
    }

    private void createStudent() {
        if (!TextUtils.isEmpty(edtName.getEditText().getText().toString()) &&
                !TextUtils.isEmpty(edtStudentCode.getEditText().getText().toString())){

            Student student = new Student(edtName.getEditText().getText().toString(), edtStudentCode.getEditText().getText().toString());

            mProgress.setTitle("Creating Student");
            mProgress.setMessage("Please wait when creating ...");
            mProgress.setCanceledOnTouchOutside(false);
            mProgress.show();

            mDatabase.child("Student").push().setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        mProgress.dismiss();
                        Toast.makeText(CreateStudentActivity.this, "Create student success!", Toast.LENGTH_SHORT).show();
                        edtName.getEditText().setText("");
                        edtStudentCode.getEditText().setText("");
                        edtName.getEditText().requestFocus();
                    } else {
                        mProgress.hide();
                        Toast.makeText(CreateStudentActivity.this, "Crate student failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
