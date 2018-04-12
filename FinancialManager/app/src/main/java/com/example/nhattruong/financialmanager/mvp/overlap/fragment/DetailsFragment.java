package com.example.nhattruong.financialmanager.mvp.overlap.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseFragment;
import com.example.nhattruong.financialmanager.dialog.dialogDayMonthYearPicker.DayMonthYearPickerDialog;
import com.example.nhattruong.financialmanager.mvp.overlap.fragment.dto.CalendarDto;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class DetailsFragment extends BaseFragment implements View.OnClickListener, DayMonthYearPickerDialog.OnClickDoneListener {

   /* @BindView(R.id.ll_month_year_view)
    LinearLayout llMonthYearView;

    @BindView(R.id.rcv_month)
    RecyclerView rvMonth;

    @BindView(R.id.imv_previous)
    ImageView imvPrevious;

    @BindView(R.id.imv_next)
    ImageView imvNext;

    @BindView(R.id.tv_month)
    TextView tvMonth;

    @BindView(R.id.tv_year)
    TextView tvYear; */

    @BindView(R.id.tv_finish)
    TextView tvFinish;

   @BindView(R.id.tv_date)
   TextView tvDate;

    @BindView(R.id.iv_calendar)
    ImageView ivCalendar;

    private CalendarAdapter adapter;
    private int month, year;
    private Date chooseDate;
    List<CalendarDto> calendarList;

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
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        chooseDate = calendar.getTime();

        tvDate.setText(DateUtils.formatFullDatePeriods(calendar.getTime()));

  /*      calendarList = new ArrayList<>();

        rvMonth.setHasFixedSize(true);
//        final GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 7);
        rvMonth.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CalendarAdapter(
                getContext(), month, year, calendarList, new CalendarAdapter.OnDateClickListener() {
            @Override
            public void onClickDate(CalendarDto date) {
                chooseDate = DateUtils.createDateFromDMY(date.getDate(), date.getMonth(), date.getYear());
                month = date.getMonth();
                year = date.getYear();
            }
        });
        rvMonth.setAdapter(adapter);
        changeListCalendar();
*/
        updateHeader();
    }

    @Override
    protected void onInitListener() {
      /*  imvNext.setOnClickListener(this);
        imvPrevious.setOnClickListener(this);
        */
        tvFinish.setOnClickListener(this);
      ivCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       /* if (view == imvPrevious) {
            showPreviousMonth();
        } else if (view == imvNext) {
            showNextMonth();
        } else if (view == tvFinish) {
            if (mListener != null) {
                mListener.onFinishClicked(chooseDate);
            }
        }*/
       if (view == ivCalendar){
           DayMonthYearPickerDialog dialog = new DayMonthYearPickerDialog(getActivity(), chooseDate, this);
           dialog.show();
       } else if (view == tvFinish){
           if (mListener!=null){
               mListener.onFinishClicked(chooseDate);
           }
       }
    }

    private void showPreviousMonth() {
        if (month == 0) {
            month = 11;
            year = year - 1;
        } else {
            month = month - 1;
        }
        updateHeader();
        changeListCalendar();
        adapter.setMonthAndYear(month, year);
    }

    private void showNextMonth() {
        if (month == 11) {
            month = 0;
            year = year + 1;
        } else {
            month = month + 1;
        }
        updateHeader();
        changeListCalendar();
        adapter.setMonthAndYear(month, year);
    }

    private void changeListCalendar() {

        if (!calendarList.isEmpty()) {
            calendarList.clear();
        }

        // date in current month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = minDay; i <= maxDay; i++) {
            CalendarDto dto = new CalendarDto(i, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
            calendarList.add(dto);
        }

        // before date if exist
        calendar.set(year, month, minDay);
        int beforeDay = DateUtils.getBeforeRemainDayOfMonth(DateUtils.getDayFromCalendarDay(calendar.get(Calendar.DAY_OF_WEEK)));
        for (int i = 0; i < beforeDay; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            int day = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            calendarList.add(0, new CalendarDto(day, month, year));
        }

        // after date if exist
        calendar.set(year, month, maxDay);
        int afterDay = DateUtils.getAfterRemainDayOfMonth(DateUtils.getDayFromCalendarDay(calendar.get(Calendar.DAY_OF_WEEK)));
        for (int i = 0; i < afterDay; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int day = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            calendarList.add(new CalendarDto(day, month, year));
        }

        adapter.notifyDataSetChanged();

    }

    private void updateHeader() {
      /*  tvMonth.setText(DateUtils.getDisplayNameOfMonth(month));
        tvYear.setText(String.valueOf(year));*/
    }

    @Override
    public void onDoneClick(Date date) {
        chooseDate = date;
        tvDate.setText(DateUtils.formatFullDatePeriods(date));
    }

    public interface ICalendarListener {
        void onFinishClicked(Date date);
    }

}
