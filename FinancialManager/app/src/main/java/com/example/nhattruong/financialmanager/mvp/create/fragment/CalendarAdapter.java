package com.example.nhattruong.financialmanager.mvp.create.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.mvp.create.fragment.dto.CalendarDto;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.DateHolder> {

    private Context context;
    private List<CalendarDto> list;
    private int month;
    private int year;
    private CalendarDto currentDate;
    private Calendar calendar;
    private OnDateClickListener listener;

    public CalendarAdapter(Context context, int month, int year, List<CalendarDto> list, OnDateClickListener listener) {
        this.context = context;
        this.list = list;
        this.month = month;
        this.year = year;
        this.listener = listener;
        calendar = Calendar.getInstance();
        currentDate = new CalendarDto(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
    }

    public void setList(List<CalendarDto> list) {
        this.list = list;
    }

    public void setMonthAndYear(int month, int year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public CalendarAdapter.DateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DateHolder(LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false));
    }

    @Override
    public void onBindViewHolder(DateHolder holder, int position) {
        CalendarDto date = list.get(position);

        // set date
        holder.tvDate.setText(String.valueOf(date.getDate()));

        if (month == date.getMonth() && year == date.getYear()) {
            holder.tvDate.setTextColor(context.getResources().getColor(R.color.black_color));
            holder.tvDate.setBackgroundResource(0);
            holder.view.setEnabled(true);
        } else {
            holder.tvDate.setTextColor(context.getResources().getColor(R.color.grey_divider));
            holder.view.setEnabled(false);
        }

        // set background date
        if (DateUtils.isCurrentDay(date, currentDate)){
            holder.tvDate.setBackgroundResource(R.drawable.bg_month_calendar_today);
            holder.tvDate.setTextColor(context.getResources().getColor(R.color.white_color));
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class DateHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        View view;
        TextView tvDate;
        TextView tvNumber;

        DateHolder(View view) {
            super(view);
            this.view = view;
            tvDate = view.findViewById(R.id.tv_date);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (this.view == view) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    currentDate = list.get(position);
                    listener.onClickDate(list.get(position));
                    notifyDataSetChanged();
                }
            }
        }

    }

    public interface OnDateClickListener {
        void onClickDate(CalendarDto date);
    }

}