package com.example.nhattruong.demofirebase.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nhattruong.demofirebase.R;
import com.example.nhattruong.demofirebase.change_info.ChangeInfoActivity;
import com.example.nhattruong.demofirebase.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvStudentCode;
    TextView tvClassCode;

    TextView tvLogout;

    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mCurrentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        tvName = findViewById(R.id.tv_name);
        tvStudentCode = findViewById(R.id.tv_student_code);
        tvClassCode = findViewById(R.id.tv_class_code);
        tvLogout = findViewById(R.id.tv_logout);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvName.setText(dataSnapshot.child("name").getValue().toString());
                tvStudentCode.setText(dataSnapshot.child("studentCode").getValue().toString());
                tvClassCode.setText(dataSnapshot.child("classCode").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        findViewById(R.id.tv_change_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoActivity.this, ChangeInfoActivity.class));
            }
        });


        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
                intent.putExtra("email", mCurrentUser.getEmail());
                startActivity(intent);
                finish();
            }
        });

    }
}
