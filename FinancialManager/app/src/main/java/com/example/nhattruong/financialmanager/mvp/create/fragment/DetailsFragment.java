package com.example.nhattruong.financialmanager.mvp.create.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseFragment;
import com.example.nhattruong.financialmanager.dialog.dialogDayMonthYearPicker.DayMonthYearPickerDialog;
import com.example.nhattruong.financialmanager.mvp.create.fragment.dto.CalendarDto;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class DetailsFragment extends BaseFragment implements View.OnClickListener, DayMonthYearPickerDialog.OnClickDoneListener {

    @BindView(R.id.edt_note)
    EditText edtNote;

    @BindView(R.id.tv_finish)
    TextView tvFinish;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.iv_calendar)
    ImageView ivCalendar;

    private Date chooseDate;

    private ICalendarListener mListener;

    public static DetailsFragment newInstance() {
        Bundle args = new Bundle();
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ICalendarListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement ICalendarListener");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_calendar_2;
    }

    @Override
    protected void onInitData() {
        Calendar calendar = Calendar.getInstance();
        chooseDate = calendar.getTime();
        tvDate.setText(DateUtils.formatFullDatePeriods(calendar.getTime()));
    }

    @Override
    protected void onInitListener() {
        tvFinish.setOnClickListener(this);
        ivCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ivCalendar) {
            DayMonthYearPickerDialog dialog = new DayMonthYearPickerDialog(getActivity(), chooseDate, this);
            dialog.show();
        } else if (view == tvFinish) {
            if (mListener != null) {
                mListener.onFinishClicked(chooseDate, edtNote.getText().toString());
            }
        }
    }

    @Override
    public void onDoneClick(Date date) {
        chooseDate = date;
        tvDate.setText(DateUtils.formatFullDatePeriods(date));
    }

    public interface ICalendarListener {
        void onFinishClicked(Date date, String note);
    }

}
