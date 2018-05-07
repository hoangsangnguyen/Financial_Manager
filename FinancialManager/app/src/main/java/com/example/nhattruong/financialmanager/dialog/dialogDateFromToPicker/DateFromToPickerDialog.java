package com.example.nhattruong.financialmanager.dialog.dialogDateFromToPicker;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseDialog;
import com.example.nhattruong.financialmanager.custom_view.DayMonthYearPickerView;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;

public class DateFromToPickerDialog
        extends BaseDialog
        implements View.OnClickListener {

    @BindView(R.id.date_from_picker_view)
    DayMonthYearPickerView dateFromPickerView;
    @BindView(R.id.date_to_picker_view)
    DayMonthYearPickerView dateToPickerView;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_done)
    TextView tvDone;

    private OnClickDoneListener listener;
    private Date dateFrom;
    private Date dateTo;

    public DateFromToPickerDialog(final Context context, Date dateFrom, Date dateTo, OnClickDoneListener listener) {
        super(context);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_date_from_to_picker;
    }

    @Override
    protected void initData() {
        if (dateFrom != null) {
            dateFromPickerView.setDate(dateFrom);
        }
        if (dateTo != null) {
            dateToPickerView.setDate(dateTo);
        }
        dateFromPickerView.setDayMonthYearPickerViewListener(new DayMonthYearPickerView.IDayMonthYearPickerViewListener() {
            @Override
            public void onDateChange(Date date) {
                if (date.after(dateToPickerView.getDate())) {
                    dateToPickerView.setDate(date);
                }
            }
        });
        dateToPickerView.setDayMonthYearPickerViewListener(new DayMonthYearPickerView.IDayMonthYearPickerViewListener() {
            @Override
            public void onDateChange(Date date) {
                if (date.before(dateFromPickerView.getDate())) {
                    dateFromPickerView.setDate(date);
                }
            }
        });
    }

    @Override
    protected void initListener() {
        tvCancel.setOnClickListener(this);
        tvDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_done:
                Date dateTo = DateUtils.resetDateEnd(dateToPickerView.getDate());
                listener.onDoneClick(dateFromPickerView.getDate(), dateTo);
                dismiss();
                break;
        }
    }

    public interface OnClickDoneListener {
        void onDoneClick(Date dateFrom, Date dateTo);
    }

}
