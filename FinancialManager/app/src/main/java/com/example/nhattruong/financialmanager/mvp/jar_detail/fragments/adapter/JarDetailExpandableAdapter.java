package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.dto.DebtDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto.JarDetailDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income.dto.IncomeDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending.dto.SpendingDTO;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JarDetailExpandableAdapter extends BaseExpandableListAdapter {

    private static final int TYPE_SPENDING = 0;
    private static final int TYPE_INCOME = 1;
    private static final int TYPE_DEBT = 2;

    private Context mContext;
    private List<JarDetailDTO> mItems;
    private OnItemDebtListener mCallbackDebt;
    private OnItemSpendingListener mCallbackDetail;

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
        return mItems.get(i).getList().size();
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
        TextView tvHeader = view.findViewById(R.id.tv_detail_header);
//        tvTitle.setText(mItems.get(i).getDate().substring(0, mItems.get(i).getDate().indexOf("T")));
        tvHeader.setText(DateUtils.formatFullDatePeriods(mItems.get(i).getDate()));
        return view;
    }

    @Override
    public int getChildTypeCount() {
        return 3;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        Object obj = mItems.get(i).getList().get(i1);

        switch (getChildType(i, i1)) {
            case TYPE_SPENDING:
            case TYPE_INCOME:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_body_row, viewGroup, false);
                DetailBodyHolder viewHolder = new DetailBodyHolder(view);
                viewHolder.bind((IJarDetail) obj, i, i1);
                view.setTag(viewHolder);
                break;
            case TYPE_DEBT:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_debt_body_row, viewGroup, false);
                DebtBodyHolder debtBodyHolder = new DebtBodyHolder(view);
                debtBodyHolder.bind((DebtDTO) obj, i, i1);
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

        @BindView(R.id.iv_detail_delete)
        ImageView ivDelete;

        DetailBodyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(IJarDetail dto, final int i, final int i1) {
            tvDetail.setText(dto.getDetail());
            tvAmount.setText(mContext.getString(R.string.currency_VND, String.valueOf((int)dto.getAmount())));
//            ivDelete.setVisibility(dto instanceof SpendingDTO ? View.VISIBLE : View.INVISIBLE);
            itemView.setBackgroundResource((i1 < mItems.get(i).getList().size() - 1) ? R.drawable.bg_white_grey_bottom : R.color.white_color);
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallbackDetail != null){
                        mCallbackDetail.onDeleteClicked(i, i1);
                    }
                }
            });
        }
    }

    class DebtBodyHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_detail_body)
        TextView tvDetail;

        @BindView(R.id.tv_detail_amount)
        TextView tvAmount;

        @BindView(R.id.iv_detail_edit)
        ImageView ivEdit;

        @BindView(R.id.iv_detail_delete)
        ImageView ivDelete;

        @BindView(R.id.tv_detail_origin)
        TextView tvOrigin;

        @BindView(R.id.tv_detail_state)
        TextView tvState;

        @BindView(R.id.rd_positive)
        RadioButton rdPositive;

        @BindView(R.id.rd_negative)
        RadioButton rdNegative;

        DebtBodyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(DebtDTO dto, final int i, final int i1) {
            tvDetail.setText(dto.getDetail());
            tvAmount.setText(mContext.getString(R.string.currency_VND, String.valueOf((int)dto.getAmount())));
            tvOrigin.setText(dto.getOrigin());
            tvState.setText(dto.getState());
            rdPositive.setChecked(dto.isPositive());
            rdNegative.setChecked(!dto.isPositive());
            itemView.setBackgroundResource((i1 < mItems.get(i).getList().size() - 1)? R.drawable.bg_white_grey_bottom : R.color.white_color);

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallbackDebt.onEditClicked(i, i1);
                }
            });

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallbackDebt.onDeleteClicked(i, i1);
                }
            });
        }

    }

    public void setItemDebtListener(OnItemDebtListener callback){
        this.mCallbackDebt = callback;
    }

    public void setItemSpendingListener(OnItemSpendingListener callback){
        this.mCallbackDetail = callback;
    }

    public interface OnItemDebtListener{
        void onEditClicked(int positionGroup, int positionChild);

        void onDeleteClicked(int positionGroup, int positionChild);
    }

    public interface OnItemSpendingListener {
        void onDeleteClicked(int positionGroup, int positionChild);
    }
}
