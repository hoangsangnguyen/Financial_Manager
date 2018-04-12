package com.example.nhattruong.financialmanager.dialog.dialogDayMonthYearPicker;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseDialog;
import com.example.nhattruong.financialmanager.custom_view.DayMonthYearPickerView;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayMonthYearPickerDialog
        extends BaseDialog
        implements View.OnClickListener {

    @BindView(R.id.day_month_year_picker_view)
    DayMonthYearPickerView pickerView;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_done)
    TextView tvDone;

    private OnClickDoneListener listener;
    private Date date;
    private boolean lockSelectPast;

    public DayMonthYearPickerDialog(final Context context, Date date, OnClickDoneListener listener) {
        super(context);
        this.date = date;
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_day_month_year_picker;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (date != null) {
            pickerView.setDate(date);
        }
    }

    @Override
    protected void initListener() {
        tvCancel.setOnClickListener(this);
        tvDone.setOnClickListener(this);

        pickerView.setDayMonthYearPickerViewListener(new DayMonthYearPickerView.IDayMonthYearPickerViewListener() {
            @Override
            public void onDateChange(Date date) {
                Date currentDate = DateUtils.getFirstDateOfDate(Calendar.getInstance().getTime());
                if (lockSelectPast && date.before(currentDate)) {
                    pickerView.setDate(currentDate);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_done:
                listener.onDoneClick(pickerView.getDate());
                dismiss();
                break;
        }
    }

    public void setLockSelectAtPast(boolean isLock) {
        this.lockSelectPast = isLock;
    }

    public interface OnClickDoneListener {
        void onDoneClick(Date date);
    }
}
