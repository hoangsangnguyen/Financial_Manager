package com.example.nhattruong.demofirebase.create_student;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhattruong.demofirebase.AppConstants;
import com.example.nhattruong.demofirebase.R;
import com.example.nhattruong.demofirebase.create_student.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDetailActivity extends AppCompatActivity {

    EditText edtName, edtCode;
    Button btnSave;
    ImageView ivBack;
    TextView tvTitle;

    private String studentId;
    private DatabaseReference mData;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        mProgress = new ProgressDialog(this);

        mData = FirebaseDatabase.getInstance().getReference();

        studentId = getIntent().getStringExtra(AppConstants.STUDENT_ID);

        ivBack = findViewById(R.id.iv_back_top_bar);
        tvTitle = findViewById(R.id.tv_title_top_bar);

        tvTitle.setText(getString(R.string.change_info));
        ivBack.setVisibility(View.VISIBLE);

        edtName = findViewById(R.id.edt_name);
        edtCode = findViewById(R.id.edt_student_code);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        loadInfo();
    }

    private void loadInfo() {
        if (TextUtils.isEmpty(studentId)) return;

        mProgress.setTitle("Loading Student");
        mProgress.setMessage("Please wait when loading ...");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();

        mData.child(AppConstants.STUDENT_NODE).child(studentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProgress.dismiss();
                Student student = dataSnapshot.getValue(Student.class);
                loadInfoSuccess(student);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mProgress.hide();
            }
        });
    }

    private void loadInfoSuccess(Student student) {
        edtName.setText(student.getName());
        edtCode.setText(student.getStudentCode());
    }

    private void saveData() {
        if (!TextUtils.isEmpty(studentId)){

            mProgress.setTitle("Saving Student");
            mProgress.setMessage("Please wait when saving ...");
            mProgress.setCanceledOnTouchOutside(false);
            mProgress.show();


            Student student= new Student(edtName.getText().toString(), edtCode.getText().toString());

            mData.child(AppConstants.STUDENT_NODE).child(studentId).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        mProgress.dismiss();
                        Toast.makeText(StudentDetailActivity.this, "Change info success!", Toast.LENGTH_SHORT).show();
                    }else {
                        mProgress.hide();
                        Toast.makeText(StudentDetailActivity.this, "Change info failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
