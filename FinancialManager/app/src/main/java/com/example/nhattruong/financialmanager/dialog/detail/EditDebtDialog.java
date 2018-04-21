package com.example.nhattruong.financialmanager.dialog.detail;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseDialog;
import com.example.nhattruong.financialmanager.dialog.dialogDayMonthYearPicker.DayMonthYearPickerDialog;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.util.Date;

import butterknife.BindView;

public class EditDebtDialog extends BaseDialog implements View.OnClickListener {

    @BindView(R.id.ln_left)
    LinearLayout llLeft;

    @BindView(R.id.tv_title_top_bar)
    TextView tvTitle;

    @BindView(R.id.tv_save_top_bar)
    TextView tvSave;

    @BindView(R.id.tv_edit_date)
    TextView tvDate;

    @BindView(R.id.iv_edit_calendar)
    ImageView ivCalendar;

    @BindView(R.id.edt_detail)
    TextInputLayout edtDetail;

    @BindView(R.id.edt_amount)
    TextInputLayout edtAmount;

    @BindView(R.id.edt_edit_origin)
    TextInputLayout edtOrigin;

    @BindView(R.id.edt_edit_state)
    TextInputLayout edtState;

    @BindView(R.id.rd_edit_negative)
    RadioButton rdNegative;

    @BindView(R.id.rd_edit_positive)
    RadioButton rdPositive;

    private IJarDetail mDebt;
    private Date dateChange;
    private OnEditDebtListener mCallback;

    private Debt getDebtChanged(){
        return new Debt.DebtBuilder()
                .setId(mDebt.getId())
                .setDate(dateChange.toString())
                .setAmount(Double.parseDouble(edtAmount.getEditText().getText().toString()))
                .setOrigin(edtOrigin.getEditText().getText().toString())
                .setState(edtState.getEditText().getText().toString())
                .setPositive(rdPositive.isChecked())
                .build();
    }

    public EditDebtDialog(Context context, IJarDetail debt, OnEditDebtListener callback) {
        super(context);
        this.mDebt = debt;
        this.mCallback = callback;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_edit_debt;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.edit_debt);
        tvDate.setText(mDebt.getDate());
        edtDetail.getEditText().setText(mDebt.getDetail());
        edtAmount.getEditText().setText(String.valueOf(mDebt.getAmount()));
        edtOrigin.getEditText().setText(mDebt.getOrigin());
        edtState.getEditText().setText(mDebt.getState());
        rdNegative.setChecked(!mDebt.isPositive());
        rdPositive.setChecked(mDebt.isPositive());
    }

    @Override
    protected void initListener() {
        llLeft.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == llLeft){
            dismiss();
        } else if (view == tvSave){
            if (mCallback != null){
                mCallback.onSaveChange(getDebtChanged());
                dismiss();
            }
        } else if (view == ivCalendar){
        }
    }

    public interface OnEditDebtListener{
        void onSaveChange(Debt debt);
    }
}
