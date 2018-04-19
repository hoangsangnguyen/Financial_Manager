package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.dto.DebtDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto.JarDetailDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income.dto.IncomeDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending.dto.SpendingDTO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JarDetailExpandableAdapter extends BaseExpandableListAdapter {

    private static final int TYPE_SPENDING = 0;
    private static final int TYPE_INCOME = 1;
    private static final int TYPE_DEBT = 2;

    private Context mContext;
    private List<JarDetailDTO> mItems;

    public JarDetailExpandableAdapter(Context mContext, List<JarDetailDTO> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    @Override
    public int getGroupCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return mItems.get(i).getList() != null ? mItems.get(i).getList().size() : 0;
    }

    @Override
    public Object getGroup(int i) {
        return mItems.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mItems.get(i).getList().get(i1);
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
        view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_header_row, viewGroup, false);
        TextView tvTitle = view.findViewById(R.id.tv_detail_header);
        tvTitle.setText(mItems.get(i).getDate());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        Object obj = mItems.get(i).getList().get(i1);

        switch (getChildType(i, i1)) {
            case TYPE_SPENDING:
            case TYPE_INCOME:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_body_row, viewGroup, false);
                DetailBodyHolder viewHolder = new DetailBodyHolder(view);
                viewHolder.bind((IJarDetail) obj);
                view.setTag(viewHolder);
                break;
            case TYPE_DEBT:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_debt_body_row, viewGroup, false);
                DebtBodyHolder debtBodyHolder = new DebtBodyHolder(view);
                debtBodyHolder.bind((DebtDTO) obj);
                view.setTag(debtBodyHolder);
                break;
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        Object obj = mItems.get(groupPosition).getList().get(childPosition);
        if (obj instanceof SpendingDTO) {
            return TYPE_SPENDING;
        } else if (obj instanceof IncomeDTO) {
            return TYPE_INCOME;
        } else if (obj instanceof DebtDTO) {
            return TYPE_DEBT;
        }

        return TYPE_SPENDING;
    }

    class DetailBodyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_detail_body)
        TextView tvDetail;

        @BindView(R.id.tv_detail_amount)
        TextView tvAmount;

        DetailBodyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(IJarDetail dto) {
            tvDetail.setText(dto.getDetail());
            tvAmount.setText(mContext.getString(R.string.currency_VND, String.valueOf(dto.getAmount())));
        }
    }

    class DebtBodyHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

        @BindView(R.id.tv_detail_body)
        TextView tvDetail;

        @BindView(R.id.tv_detail_amount)
        TextView tvAmount;

        @BindView(R.id.tv_detail_origin)
        TextView tvOrigin;

        @BindView(R.id.tv_detail_state)
        TextView tvState;

        @BindView(R.id.rd_positive)
        RadioButton rdPositive;

        @BindView(R.id.rd_negative)
        RadioButton rdNegative;

        public DebtBodyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            rdNegative.setOnCheckedChangeListener(this);
            rdPositive.setOnCheckedChangeListener(this);
        }

        public void bind(DebtDTO dto) {
            tvDetail.setText(dto.getDetail());
            tvAmount.setText(mContext.getString(R.string.currency_VND, String.valueOf(dto.getAmount())));
            tvOrigin.setText(dto.getOrigin());
            tvState.setText(dto.getState());
            rdPositive.setChecked(dto.isPositive());
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        }
    }
}
