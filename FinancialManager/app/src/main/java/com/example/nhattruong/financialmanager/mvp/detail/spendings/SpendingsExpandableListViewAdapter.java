package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.DateSpendings;
import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.List;

public class SpendingsExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DateSpendings> dateSpendingsList;

    SpendingsExpandableListViewAdapter(Context context, List<DateSpendings> dateSpendingsList) {
        this.context = context;
        this.dateSpendingsList = dateSpendingsList;
    }

    @Override
    public int getGroupCount() {
        return dateSpendingsList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return dateSpendingsList.get(i).getSpendingList().size();
    }

    @Override
    public Object getGroup(int i) {
        return dateSpendingsList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return dateSpendingsList.get(i).getSpendingList().get(i1);
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
        tvDateItemsDetail.setText(String.valueOf(dateSpendingsList.get(i).getDate()));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.items_detail, viewGroup, false);
        TextView tvNameItemsDetail = view.findViewById(R.id.tv_name_items_detail);
        TextView tvAmountItemsDetail = view.findViewById(R.id.tv_amount_items_detail);
        Spending spending = dateSpendingsList.get(i).getSpendingList().get(i1);
        tvNameItemsDetail.setText(spending.getDetail());
        tvAmountItemsDetail.setText(String.valueOf(spending.getAmount()));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
