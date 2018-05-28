package com.example.nhattruong.demofirebase.create_student.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhattruong.demofirebase.R;
import com.example.nhattruong.demofirebase.create_student.model.Student;

import java.util.List;

/**
 * Created by nhattruong on 4/15/2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private Context mContext;
    private List<Student> mItems;
    private OnListener mCallback;

    public StudentAdapter(Context mContext, List<Student> mItems, OnListener callback) {
        this.mContext = mContext;
        this.mItems = mItems;
        this.mCallback = callback;
    }

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        Student item = mItems.get(position);
        holder.tvName.setText(item.getName());
        holder.tvCode.setText(item.getStudentCode());
        holder.tvBottomLine.setVisibility(position<mItems.size() - 1 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvCode, tvBottomLine;
        ImageView ivDelete;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvBottomLine = itemView.findViewById(R.id.tv_bottom_line);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallback != null){
                        mCallback.onDelete(getAdapterPosition());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallback != null){
                        mCallback.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnListener {
        void onDelete(int position);

        void onItemClicked(int position);
    }
}
