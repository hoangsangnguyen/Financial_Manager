package com.example.nhattruong.financialmanager.mvp.detail.debts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.Debt;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DebtsRecyclerViewAdapter extends RecyclerView.Adapter<DebtsRecyclerViewAdapter.DebtsViewHolder> {

    private Context context;
    private List<Debt> debtList;

    DebtsRecyclerViewAdapter(Context context, List<Debt> debtList) {
        this.context = context;
        this.debtList = debtList;
    }

    @Override
    public DebtsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_detail, parent, false);
        return new DebtsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DebtsViewHolder holder, int position) {
        Debt debt = debtList.get(position);
        holder.tvDateItemsDetail.setText(debt.getDate());
        holder.tvNameItemsDetail.setText(debt.getDetail());
        holder.tvAmountItemsDetail.setText(String.valueOf(debt.getAmount()));
    }

    @Override
    public int getItemCount() {
        return debtList != null ? debtList.size() : 0;
    }

    static class DebtsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date_items_detail)
        TextView tvDateItemsDetail;
        @BindView(R.id.tv_name_items_detail)
        TextView tvNameItemsDetail;
        @BindView(R.id.tv_amount_items_detail)
        TextView tvAmountItemsDetail;

        DebtsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
