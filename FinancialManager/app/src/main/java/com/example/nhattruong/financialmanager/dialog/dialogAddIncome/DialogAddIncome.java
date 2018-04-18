package com.example.nhattruong.financialmanager.dialog.dialogAddIncome;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogAddIncome extends BaseDialog implements View.OnClickListener {

    @BindView(R.id.ll_view)
    LinearLayout llView;

    @BindView(R.id.tv_income_for_jar)
    TextView tvIncomeForJar;

    @BindView(R.id.tv_general_income)
    TextView tvGeneralIncome;

    public DialogAddIncome(Context context, onClickItemListener callBack) {
        super(context);
        this.callBack = callBack;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_add_income;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        llView.setOnClickListener(this);
        tvIncomeForJar.setOnClickListener(this);
        tvGeneralIncome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == llView){
            dismiss();
        } else if (view == tvIncomeForJar){
            callBack.onAddIncomeForJar();
            dismiss();
        } else if (view == tvGeneralIncome){
            callBack.onAddGeneralIncome();
            dismiss();
        }
    }

    public interface onClickItemListener{
        void onAddIncomeForJar();

        void onAddGeneralIncome();
    }

    private onClickItemListener callBack;
}
