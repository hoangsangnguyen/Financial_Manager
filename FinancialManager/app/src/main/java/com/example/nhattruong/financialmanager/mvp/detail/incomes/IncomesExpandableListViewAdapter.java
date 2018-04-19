package com.example.nhattruong.financialmanager.mvp.detail.incomes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.DateIncomes;
import com.example.nhattruong.financialmanager.model.Income;

import java.util.List;

public class IncomesExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DateIncomes> dateIncomesList;

    IncomesExpandableListViewAdapter(Context context, List<DateIncomes> dateIncomesList) {
        this.context = context;
        this.dateIncomesList = dateIncomesList;
    }

    @Override
    public int getGroupCount() {
        return dateIncomesList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return dateIncomesList.get(i).getIncomeList().size();
    }

    @Override
    public Object getGroup(int i) {
        return dateIncomesList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return dateIncomesList.get(i).getIncomeList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.items_detail_header, viewGroup, false);
        TextView tvDateItemsDetail = view.findViewById(R.id.tv_date_items_detail);
        tvDateItemsDetail.setText(String.valueOf(dateIncomesList.get(i).getDate()));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.items_detail, viewGroup, false);
        TextView tvNameItemsDetail = view.findViewById(R.id.tv_name_items_detail);
        TextView tvAmountItemsDetail = view.findViewById(R.id.tv_amount_items_detail);
        Income income = dateIncomesList.get(i).getIncomeList().get(i1);
        tvNameItemsDetail.setText(income.getDetail());
        tvAmountItemsDetail.setText(String.valueOf(income.getAmount()));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
