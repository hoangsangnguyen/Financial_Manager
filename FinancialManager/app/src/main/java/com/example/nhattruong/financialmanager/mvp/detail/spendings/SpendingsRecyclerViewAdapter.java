package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.Spending;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpendingsRecyclerViewAdapter extends RecyclerView.Adapter<SpendingsRecyclerViewAdapter.SpendingsViewHolder> {

    private Context context;
    private List<Spending> spendingList;

    SpendingsRecyclerViewAdapter(Context context, List<Spending> spendingList) {
        this.context = context;
        this.spendingList = spendingList;
    }

    @Override
    public SpendingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_detail, parent, false);
        return new SpendingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpendingsViewHolder holder, int position) {
        Spending spending = spendingList.get(position);
        holder.tvDateDetail.setText(spending.getDate());
        holder.tvNameDetail.setText(spending.getDetail());
        holder.tvAmountDetail.setText(String.valueOf(spending.getAmount()));
    }

    @Override
    public int getItemCount() {
        return spendingList != null ? spendingList.size() : 0;
    }

    static class SpendingsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date_items_detail)
        TextView tvDateDetail;
        @BindView(R.id.tv_name_items_detail)
        TextView tvNameDetail;
        @BindView(R.id.tv_amount_items_detail)
        TextView tvAmountDetail;

        SpendingsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
