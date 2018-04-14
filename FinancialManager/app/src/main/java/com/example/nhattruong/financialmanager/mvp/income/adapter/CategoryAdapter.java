package com.example.nhattruong.financialmanager.mvp.income.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.Jar;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Jar> items;
    private Context context;
    private OnItemClickedListener mCallback;

    public CategoryAdapter(Context context, List<Jar> items, OnItemClickedListener callback) {
        this.items = items;
        this.context = context;
        this.mCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        CircleImageView ivCategory;
        TextView tvCategory;

        CategoryViewHolder(View itemView) {
            super(itemView);

            ivCategory = itemView.findViewById(R.id.iv_category);
            tvCategory = itemView.findViewById(R.id.tv_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallback != null){
                        mCallback.onItemClicked(items.get(getAdapterPosition()).getId());
                    }
                }
            });
        }

        void bind(Jar category) {
            ivCategory.setImageResource(R.drawable.ic_transport);
            tvCategory.setText(category.getType());
        }
    }

    public interface OnItemClickedListener {
        void onItemClicked(String id);
    }
}
