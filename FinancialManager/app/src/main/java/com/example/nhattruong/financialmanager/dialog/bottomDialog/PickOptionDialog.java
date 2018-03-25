package com.example.nhattruong.financialmanager.dialog.bottomDialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PickOptionDialog extends BaseDialog implements BottomDialogAdapter.OnItemBottomClick {
    @BindView(R.id.rcv_item)
    RecyclerView rcvItem;

    private List<String> mList;
    private IOptionDialogListener mListener;

    public PickOptionDialog(@NonNull Context context, List<String> strings, IOptionDialogListener listener) {
        super(context);
        this.mList = strings;
        this.mListener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_pick_option_dialog;
    }

    @Override
    protected void initData() {
        rcvItem.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcvItem.setAdapter(new BottomDialogAdapter(mList, this.getContext(), this));
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_cancel)
    void onCancelDialog() {
        dismiss();

    }

    @OnClick(R.id.ln_content_dialog)
    void onCancelDialog2() {
        dismiss();
    }

    @Override
    public void onClickPosition(int position) {
        dismiss();
        mListener.onClickItem(position);
    }

    public interface IOptionDialogListener {
        void onClickItem(int position);
    }

}
