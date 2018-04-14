package com.example.nhattruong.financialmanager.mvp.detail.incomes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.Income;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncomesRecyclerViewAdapter extends RecyclerView.Adapter<IncomesRecyclerViewAdapter.IncomesViewHolder> {

    private Context context;
    private List<Income> incomeList;

    IncomesRecyclerViewAdapter(Context context, List<Income> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    @Override
    public IncomesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_detail, parent, false);
        return new IncomesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncomesViewHolder holder, int position) {
        Income income = incomeList.get(position);
        holder.tvDateItemsDetail.setText(income.getDate());
        holder.tvNameItemsDetail.setText(income.getDetail());
        holder.tvAmountItemsDetail.setText(String.valueOf(income.getAmount()));
    }

    @Override
    public int getItemCount() {
        return incomeList != null ? incomeList.size() : 0;
    }

    static class IncomesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date_items_detail)
        TextView tvDateItemsDetail;
        @BindView(R.id.tv_name_items_detail)
        TextView tvNameItemsDetail;
        @BindView(R.id.tv_amount_items_detail)
        TextView tvAmountItemsDetail;

        IncomesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
