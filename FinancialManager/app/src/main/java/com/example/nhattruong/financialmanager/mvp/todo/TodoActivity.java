package com.example.nhattruong.financialmanager.mvp.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.DialogOk;
import com.example.nhattruong.financialmanager.dialog.dialogDayMonthYearPicker.DayMonthYearPickerDialog;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.Todo;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;

public class TodoActivity extends BaseActivity implements View.OnClickListener, TodoContract.View {

    @BindView(R.id.ln_left)
    LinearLayout lnLeft;

    @BindView(R.id.tv_title_top_bar)
    TextView tvTitle;

    @BindView(R.id.tv_right_top_bar)
    TextView tvTopRight;

    @BindView(R.id.edt_content_todo)
    EditText edtContent;

    @BindView(R.id.tv_date_todo)
    TextView tvDate;

    @BindView(R.id.iv_calendar_todo)
    ImageView ivCalendar;

    @Override
    public TodoPresenter getPresenter() {
        return (TodoPresenter) super.getPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new TodoPresenter());
        setContentView(R.layout.activity_todo);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInitData() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(R.string.todo);
        tvTopRight.setVisibility(View.VISIBLE);
        tvTopRight.setText(R.string.create);
    }

    @Override
    public void onInitListener() {
        lnLeft.setOnClickListener(this);
        tvTopRight.setOnClickListener(this);
        ivCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == lnLeft){
            onBackPressed();
        } else if (view == tvTopRight){
            getPresenter().creteTodo(edtContent.getText().toString().trim());
        } else if (view == ivCalendar){
            DayMonthYearPickerDialog dialog = new DayMonthYearPickerDialog(this, getPresenter().getDate(), new DayMonthYearPickerDialog.OnClickDoneListener() {
                @Override
                public void onDoneClick(Date date) {
                    getPresenter().setDate(date);
                    tvDate.setText(DateUtils.formatFullDatePeriods(date));
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onSuccess(final Todo todo) {
        showOkDialog(getString(R.string.app_name), "Create todo success!", new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                Intent intent = new Intent(TodoActivity.this, HomeActivity.class);
                intent.putExtra(AppConstants.TODO, todo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onFailure(RestError restError) {
        showErrorDialog(restError.message);
    }
}
