package com.example.nhattruong.financialmanager.dialog.detail;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.dialog.dialogDayMonthYearPicker.DayMonthYearPickerDialog;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditDebtDialog extends Dialog implements View.OnClickListener, DayMonthYearPickerDialog.OnClickDoneListener {

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
    private Date currentDate;
    private OnEditDebtListener mCallback;

    private Debt getDebtChanged(){
        return new Debt.DebtBuilder()
                .setId(mDebt.getId())
                .setDate(currentDate)
                .setDetail(edtDetail.getEditText().getText().toString())
                .setAmount(Double.parseDouble(edtAmount.getEditText().getText().toString()))
                .setOrigin(edtOrigin.getEditText().getText().toString())
                .setState(edtState.getEditText().getText().toString())
                .setPositive(rdPositive.isChecked())
                .build();
    }

    public EditDebtDialog(Context context, IJarDetail debt, Date date, OnEditDebtListener callback) {
        super(context, R.style.FullscreenDialogEditDebt);
        this.mDebt = debt;
        this.currentDate = date;
        this.mCallback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();

        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(
                    ContextCompat.getColor(
                            getContext(),
                            R.color.app_color)
            );
        }

        setContentView(R.layout.dialog_edit_debt);
        ButterKnife.bind(this);

        initData();
        initListener();
    }


    private void initData() {
        tvTitle.setText(R.string.edit_debt);
        tvDate.setText(DateUtils.formatFullDatePeriods(currentDate));
        edtDetail.getEditText().setText(mDebt.getDetail());
        edtAmount.getEditText().setText(String.valueOf(mDebt.getAmount()));
        edtOrigin.getEditText().setText(mDebt.getOrigin());
        edtState.getEditText().setText(mDebt.getState());
        rdNegative.setChecked(!mDebt.isPositive());
        rdPositive.setChecked(mDebt.isPositive());
        tvSave.setVisibility(View.VISIBLE);
    }

    private void initListener() {
        llLeft.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        ivCalendar.setOnClickListener(this);
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
            DayMonthYearPickerDialog dialog = new DayMonthYearPickerDialog(getContext(), currentDate, this);
            dialog.show();
        }
    }

    @Override
    public void onDoneClick(Date date) {
        currentDate = date;
        tvDate.setText(DateUtils.formatFullDatePeriods(currentDate));
    }

    public interface OnEditDebtListener{
        void onSaveChange(Debt debt);
    }
}
