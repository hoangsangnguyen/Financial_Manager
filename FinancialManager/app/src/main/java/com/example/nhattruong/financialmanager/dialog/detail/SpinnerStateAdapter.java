package com.example.nhattruong.financialmanager.dialog.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;

import java.util.List;

public class SpinnerStateAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mItems;
    private int mSelectedPosition;
    private ISpinnerCallback mCallback;

    public SpinnerStateAdapter(Context context, List<String> items, ISpinnerCallback callback) {
        this.mContext = context;
        this.mItems = items;
        this.mCallback = callback;
    }

    public void setTextView(TextView tv) {
        this.mSelectedPosition = mItems.indexOf(tv.getText().toString());
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_state_spinner, viewGroup,false);
        }

        TextView names = view.findViewById(R.id.tv_text);
        names.setText(mItems.get(i));

        View vDivider = view.findViewById(R.id.v_divider);
        vDivider.setVisibility(i < (mItems.size() - 1) ? View.VISIBLE : View.GONE);
        names.setSelected(i == mSelectedPosition);
        names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null && mSelectedPosition != i)
                    mCallback.onItemSelected(mItems.get(i));
            }
        });
        return view;
    }

    public interface ISpinnerCallback{
        void onItemSelected(String state);
    }
}
