package com.example.nhattruong.financialmanager.mvp.create.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;

import java.util.List;

public class CalculatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Integer> mItems;
    private OnItemClickedListener mCallback;

    public CalculatorAdapter(Context context, List<Integer> items, OnItemClickedListener callback) {
        this.mContext = context;
        this.mItems = items;
        this.mCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CalculatorHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_number_calculator, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CalculatorHolder calculatorHolder = (CalculatorHolder) holder;
        if (position == mItems.size() - 1){
            calculatorHolder.ivCalculator.setVisibility(View.VISIBLE);
            calculatorHolder.tvNumber.setVisibility(View.GONE);
            calculatorHolder.ivCalculator.setImageResource(mItems.get(position));
        } else if (position == 9){
            calculatorHolder.ivCalculator.setVisibility(View.GONE);
            calculatorHolder.tvNumber.setVisibility(View.VISIBLE);
            calculatorHolder.tvNumber.setText(".");
        } else {
            calculatorHolder.ivCalculator.setVisibility(View.GONE);
            calculatorHolder.tvNumber.setVisibility(View.VISIBLE);
            calculatorHolder.tvNumber.setText(String.valueOf(mItems.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return mItems!=null? mItems.size():0;
    }

    public class CalculatorHolder extends RecyclerView.ViewHolder {
        TextView tvNumber;
        ImageView ivCalculator;

        CalculatorHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number_calculator);
            ivCalculator = itemView.findViewById(R.id.iv_calculator);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallback!= null){
                        mCallback.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickedListener{
        void onItemClicked(int position);
    }
}
