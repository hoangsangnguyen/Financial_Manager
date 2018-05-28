package com.example.nhattruong.demofirebase.create_student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhattruong.demofirebase.AppConstants;
import com.example.nhattruong.demofirebase.R;
import com.example.nhattruong.demofirebase.create_student.adapter.StudentAdapter;
import com.example.nhattruong.demofirebase.create_student.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity {

    RecyclerView rcv;
    ImageView ivBack;
    TextView tvTitle;

    private List<Student> mList;
    private List<String> mListId;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        mProgress = new ProgressDialog(this);

        mList = new ArrayList<>();
        mListId = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        tvTitle = findViewById(R.id.tv_title_top_bar);
        tvTitle.setText(R.string.list_student);

        ivBack = findViewById(R.id.iv_back_top_bar);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rcv = findViewById(R.id.rcv_student);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(new StudentAdapter(this, mList, new StudentAdapter.OnListener() {
            @Override
            public void onDelete(int position) {
                deleteStudent(position);
            }

            @Override
            public void onItemClicked(int position) {
                Intent intentDetail = new Intent(ListStudentActivity.this, StudentDetailActivity.class);
                intentDetail.putExtra(AppConstants.STUDENT_ID, mListId.get(position));
                startActivity(intentDetail);
            }
        }));

        loadData();
    }

    private void deleteStudent(int position) {
        mProgress.setTitle("Delete " + mList.get(position).toString());
        mProgress.setMessage("Please wait when deleting ...");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        mDatabase.child(AppConstants.STUDENT_NODE).child(mListId.get(position)).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    mProgress.dismiss();
                }else {
                    Toast.makeText(ListStudentActivity.this, "Delete student failed!", Toast.LENGTH_SHORT).show();
                    mProgress.hide();
                }
            }
        });
    }

    private void loadData() {
        mProgress.setTitle("Loading Students");
        mProgress.setMessage("Please wait when loading ...");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();

        mDatabase.child(AppConstants.STUDENT_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!mList.isEmpty()) mList.clear();
                if (!mListId.isEmpty()) mListId.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    mListId.add(item.getKey());
                    mList.add(item.getValue(Student.class));
                }
                rcv.getAdapter().notifyDataSetChanged();
                mProgress.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mProgress.hide();
            }
        });
    }
}
