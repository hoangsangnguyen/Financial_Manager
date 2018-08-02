package com.example.nhattruong.financialmanager.mvp.todo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.databinding.ItemRowTodoBinding;
import com.example.nhattruong.financialmanager.model.Todo;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;
    private List<Todo> items;
    private Listener listener;

    public TodoAdapter(Context context, List<Todo> items, Listener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRowTodoBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_row_todo, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRowTodoBinding binding;

        public ViewHolder(ItemRowTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Todo todo){
            binding.tvContent.setText(todo.getName());
            binding.tvDate.setText(DateUtils.formatFullDatePeriods(todo.getDate()));
            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onDeleted(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface Listener{
        void onDeleted(int position);
    }
}
