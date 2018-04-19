package com.example.nhattruong.financialmanager.mvp.jar_detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.TabHeader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JarDetailTabHeaderAdapter extends RecyclerView.Adapter<JarDetailTabHeaderAdapter.HeaderHolder> {

    private Context context;
    private List<TabHeader> items;
    private HeaderAdapterListener callback;
    private int mCurrentSelected = 0;

    public JarDetailTabHeaderAdapter(Context context, List<TabHeader> items, HeaderAdapterListener callback) {
        this.context = context;
        this.items = items;
        this.callback = callback;
    }

    public void setSelectedTab(int position) {
        this.mCurrentSelected = position;
    }

    @Override
    public HeaderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindViewHolder(HeaderHolder holder, int position) {
        TabHeader header = items.get(position);
        holder.tvHeaderName.setSelected(mCurrentSelected == position);
        holder.tvHeaderName.setText(header.getName());
        holder.tvHeaderName.setWidth(getWidthMax());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_header_name)
        TextView tvHeaderName;

        HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvHeaderName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.onTabSelected(getAdapterPosition());
        }
    }

    public interface HeaderAdapterListener {
        void onTabSelected(int position);
    }

    private int getWidthMax() {
        String max = items.get(0).getName();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().length() > max.length()) {
                max = items.get(i).getName();
            }
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_header, null, false);
        TextView tvHeaderName = view.findViewById(R.id.tv_header_name);
        tvHeaderName.setText(max);
        tvHeaderName.measure(0, 0);
        return tvHeaderName.getMeasuredWidth();
    }
}
