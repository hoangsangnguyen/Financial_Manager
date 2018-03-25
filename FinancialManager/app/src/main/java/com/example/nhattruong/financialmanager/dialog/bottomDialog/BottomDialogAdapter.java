package com.example.nhattruong.financialmanager.dialog.bottomDialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomDialogAdapter extends RecyclerView.Adapter<BottomDialogAdapter.ItemHolder> {
    private List<String> mLists;
    private Context mContext;
    private OnItemBottomClick mCallback;

    public BottomDialogAdapter(List<String> mLists, Context mContext, OnItemBottomClick mCallback) {
        this.mLists = mLists;
        this.mContext = mContext;
        this.mCallback = mCallback;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_bottom_dialog, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvName.setText(mLists.get(position));
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onClickPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLists != null ? mLists.size() : 0;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemBottomClick {

        void onClickPosition(int position);
    }
}
