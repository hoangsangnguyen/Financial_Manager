package com.example.nhattruong.financialmanager.mvp.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.DialogPositiveNegative;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.mvp.todo.adapter.TodoAdapter;

import butterknife.BindView;

public class TodoListActivity extends BaseActivity implements TodoListContract.View {

    @BindView(R.id.ln_left)
    LinearLayout lnLeft;

    @BindView(R.id.tv_title_top_bar)
    TextView tvTitle;

    @BindView(R.id.iv_right_top_bar)
    ImageView ivRight;

    @BindView(R.id.rcv)
    RecyclerView rcv;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    private TodoAdapter adapter;

    @Override
    public TodoListPresenter getPresenter() {
        return (TodoListPresenter) super.getPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new TodoListPresenter());
        setContentView(R.layout.activity_todo_list);
        super.onCreate(savedInstanceState);

        initData();
        initListener();
    }

    private void initData() {
        tvTitle.setText("Todo list");
        ivRight.setImageDrawable(getDrawable(R.drawable.ic_add_transparent));
        ivRight.setVisibility(View.VISIBLE);

        adapter = new TodoAdapter(this, getPresenter().getTodoList(), new TodoAdapter.Listener() {
            @Override
            public void onDeleted(final int position) {
                showConfirmDialog("", "Are you sure to delete this?", new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        getPresenter().remove(position);
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {

                    }
                });
            }
        });
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);

        if (getPresenter().getTodoList().isEmpty()) tvNoData.setVisibility(View.VISIBLE);
    }

    private void initListener() {
        lnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodoListActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TodoListActivity.this, TodoActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TodoListActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void deleteSuccess() {
        adapter.notifyDataSetChanged();
        if (getPresenter().getTodoList().isEmpty()) tvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void deleteFailed() {
        showErrorDialog("Delete Failed!");
    }
}
