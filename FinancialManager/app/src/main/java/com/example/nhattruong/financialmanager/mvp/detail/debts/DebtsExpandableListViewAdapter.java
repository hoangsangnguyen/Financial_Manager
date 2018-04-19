package com.example.nhattruong.financialmanager.mvp.detail.debts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.DateDebts;
import com.example.nhattruong.financialmanager.model.Debt;

import java.util.List;

import butterknife.ButterKnife;

public class DebtsExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DateDebts> dateDebtsList;

    DebtsExpandableListViewAdapter(Context context, List<DateDebts> dateDebtsList) {
        this.context = context;
        this.dateDebtsList = dateDebtsList;
    }

    @Override
    public int getGroupCount() {
        return dateDebtsList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return dateDebtsList.get(i).getDebtList().size();
    }

    @Override
    public Object getGroup(int i) {
        return dateDebtsList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return dateDebtsList.get(i).getDebtList().get(i1);
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
        tvDateItemsDetail.setText(String.valueOf(dateDebtsList.get(i).getDate()));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.items_detail, viewGroup, false);
        ButterKnife.bind(this, view);
        Debt debt = dateDebtsList.get(i).getDebtList().get(i1);
        TextView tvNameItemsDetail = view.findViewById(R.id.tv_name_items_detail);
        tvNameItemsDetail.setText(debt.getDetail());
        TextView tvAmountItemsDetail = view.findViewById(R.id.tv_amount_items_detail);
        tvAmountItemsDetail.setText(String.valueOf(debt.getAmount()));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
