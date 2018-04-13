package com.example.nhattruong.financialmanager.mvp.login.detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.Detail;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsDetailAdapter extends RecyclerView.Adapter<ItemsDetailAdapter.DataViewHolder> {

    Context context;
    List<Detail> detailList;

    public ItemsDetailAdapter(Context context, List<Detail> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_detail, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.tvTitle.setText(detailList.get(position).getTitle());
        holder.tvMoneyChi.setText(String.valueOf(detailList.get(position).getMoneyChi()));
        holder.tvMoneyThu.setText(String.valueOf(detailList.get(position).getMoneyThu()));
    }

    @Override
    public int getItemCount() {
        return detailList != null ? detailList.size() : 0;
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_items_detail)
        TextView tvTitle;
        @BindView(R.id.tv_money_thu_items_detail)
        TextView tvMoneyThu;
        @BindView(R.id.tv_money_chi_items_detail)
        TextView tvMoneyChi;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
