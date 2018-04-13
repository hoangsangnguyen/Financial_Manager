package com.example.nhattruong.financialmanager.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.Jar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JarAdapter extends RecyclerView.Adapter<JarAdapter.ViewHolder> {

    private Context mContext;
    private List<Jar> mItems;
    private Listener mCallback;

    public JarAdapter(Context mContext, List<Jar> mItems, Listener listener) {
        this.mContext = mContext;
        this.mItems = mItems;
        this.mCallback = listener;
    }

    @Override
    public JarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_jar, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JarAdapter.ViewHolder holder, int position) {
        Jar jar = mItems.get(position);
        if (jar != null && position != 6) { // type "Tổng hợp"
            double rest = jar.getIncomes() - jar.getSpending() - jar.getDebts();
            holder.tvQuantity.setText(String.valueOf(rest));
            holder.tvType.setText(jar.getType());
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_jar)
        ImageView ivJar;

        @BindView(R.id.tv_quantity_money)
        TextView tvQuantity;

        @BindView(R.id.tv_type)
        TextView tvType;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallback != null) {
                        mCallback.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface Listener {
        void onItemClicked(int position);

    }
}
