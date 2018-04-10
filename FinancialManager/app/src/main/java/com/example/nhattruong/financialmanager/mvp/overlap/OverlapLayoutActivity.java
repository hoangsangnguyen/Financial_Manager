package com.example.nhattruong.financialmanager.mvp.overlap;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.model.Category;
import com.example.nhattruong.financialmanager.mvp.overlap.adapter.CalculatorAdapter;
import com.example.nhattruong.financialmanager.mvp.overlap.adapter.CategoryAdapter;
import com.example.nhattruong.financialmanager.mvp.overlap.fragment.CalendarFragment;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class OverlapLayoutActivity extends BaseActivity implements View.OnClickListener, CalendarFragment.ICalendarListener {

    @BindView(R.id.iv_back_left)
    ImageView ivLeftBack;

    @BindView(R.id.frm_header)
    FrameLayout frmHeader;

    @BindView(R.id.frm_body)
    FrameLayout frmBody;

    @BindView(R.id.ll_main)
    LinearLayout llMain;

    @BindView(R.id.ll_header)
    LinearLayout llHeader;

    @BindView(R.id.ll_in_out_come)
    LinearLayout llInOutCome;

    @BindView(R.id.ll_calculator)
    LinearLayout llCalculator;

    @BindView(R.id.ll_category)
    LinearLayout llCategory;

    @BindView(R.id.ll_details)
    LinearLayout llDetail;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_currency_income_outcome)
    TextView tvCurrency;

    @BindView(R.id.tv_income)
    TextView tvIncome;

    @BindView(R.id.tv_outcome)
    TextView tvOutcome;

    @BindView(R.id.tv_next)
    TextView tvNext;

    @BindView(R.id.tv_details)
    TextView tvDetail;

    @BindView(R.id.rcv_calculator)
    RecyclerView rcvCalculator;

    @BindView(R.id.rcv_category)
    RecyclerView rcvCategory;

    private boolean isFirstInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_overlap_layout);
        super.onCreate(savedInstanceState);

        initData();
        initListener();
    }

    private void initData() {

        final List<Integer> numberList = new ArrayList<>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);
        numberList.add(4);
        numberList.add(5);
        numberList.add(6);
        numberList.add(7);
        numberList.add(8);
        numberList.add(9);
        numberList.add(R.drawable.ic_keyboard_delete);
        numberList.add(0);
        numberList.add(R.drawable.ic_keyboard_delete);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Transport", R.drawable.ic_transport));
        categories.add(new Category("Bills", R.drawable.ic_bills));
        categories.add(new Category("Sport", R.drawable.ic_dumbbell));
        categories.add(new Category("Education", R.drawable.ic_education));
        categories.add(new Category("Home", R.drawable.ic_home));
        categories.add(new Category("Travel", R.drawable.ic_travel));
        categories.add(new Category("Food", R.drawable.ic_food));

        rcvCalculator.setLayoutManager(new GridLayoutManager(this, 3));
        rcvCalculator.setHasFixedSize(true);
        rcvCalculator.setAdapter(new CalculatorAdapter(this, numberList, new CalculatorAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                Toast.makeText(OverlapLayoutActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
                String currency = tvCurrency.getText().toString();
                if (position >= numberList.size() - 1 && position != 9) {
                    if (!TextUtils.isEmpty(currency)) {
                        if (currency.length() == 1) {
                            tvCurrency.setText("");
                            isFirstInput = true;
                        } else {
                            currency = currency.substring(0, currency.length() - 1);
                            tvCurrency.setText(currency);
                        }
                    }
                } else if (position == 10 && !TextUtils.isEmpty(tvCurrency.getText())) {
                    tvCurrency.setText(currency.concat(String.valueOf(0)));
                } else {
                    if (isFirstInput) {
                        tvCurrency.setText(tvIncome.isSelected()
                                ? "+" + currency.concat(String.valueOf(position + 1))
                                : currency.concat(String.valueOf(0 - position - 1))
                        );
                        isFirstInput = false;
                    } else {
                        tvCurrency.setText(currency.concat(String.valueOf(position + 1)));
                    }
                }
            }
        }));

        rcvCategory.setLayoutManager(new GridLayoutManager(this, 3));
        rcvCategory.setAdapter(new CategoryAdapter(this, categories));

        tvOutcome.setSelected(true);

        initFragmentDatail();
    }

    private void initFragmentDatail() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CalendarFragment calendarFragment = CalendarFragment.newInstance();
        fragmentTransaction.add(R.id.ll_details, calendarFragment);
        fragmentTransaction.commit();

    }

    private void initListener() {
        tvIncome.setOnClickListener(this);
        tvOutcome.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        tvDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tvNext) {

            llCalculator.setVisibility(View.GONE);
            llCategory.setVisibility(View.VISIBLE);
            llInOutCome.setVisibility(View.GONE);
            showCategoryView();

        } else if (view == ivLeftBack) {
            onBackPressed();
        } else if (view == tvIncome) {
            if (!TextUtils.isEmpty(tvCurrency.getText())) {
                String textCurrency = tvCurrency.getText().toString().substring(1);
                int currency = Math.abs(Integer.valueOf(textCurrency));
                tvCurrency.setText(getString(R.string.position_number, currency));
            }

            llMain.setSelected(true);
            tvIncome.setSelected(true);
            tvOutcome.setSelected(false);
        } else if (view == tvOutcome) {
            if (!TextUtils.isEmpty(tvCurrency.getText())) {
                String textCurrency = tvCurrency.getText().toString().substring(1);
                int currency = Integer.valueOf(textCurrency);
                if (currency >= 0) {
                    tvCurrency.setText(String.valueOf(0 - currency));
                }
            }
            llMain.setSelected(false);
            tvIncome.setSelected(false);
            tvOutcome.setSelected(true);
        } else if (view == tvDetail){
            llDetail.setVisibility(View.VISIBLE);
            rcvCategory.setVisibility(View.INVISIBLE);
            tvDetail.setOnClickListener(null);
            animation(llCategory);
        }

    }

    private void showCategoryView() {

        DisplayMetrics metrics = getResources().getDisplayMetrics();

        tvTitle.setText(tvIncome.isSelected() ? "INCOME" : "OUTCOME");

        LinearLayout.LayoutParams lpHeader = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        llHeader.setLayoutParams(lpHeader);

        LinearLayout.LayoutParams lpTop = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        frmHeader.setLayoutParams(lpTop);

        LinearLayout.LayoutParams lpCurrency = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        /*float marginDp = 50f;
        float fpixelsMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginDp, metrics);*/
        lpCurrency.topMargin = 50;
        tvCurrency.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        tvCurrency.setLayoutParams(lpCurrency);

        LinearLayout.LayoutParams lpBody = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
        );

        float dp = 50f;
        float fpixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
        lpBody.topMargin = (int) (fpixels + 0.5f);
        frmBody.setLayoutParams(lpBody);
    }

    private void animation(final LinearLayout frmLayout) {
        final int left, top, right;
        FrameLayout.LayoutParams linearLayout = (FrameLayout.LayoutParams) frmLayout.getLayoutParams();
        left = linearLayout.leftMargin;
        right = linearLayout.rightMargin;
        top = linearLayout.topMargin;

        ValueAnimator va = ValueAnimator.ofInt(20);
        va.setDuration(200);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) frmLayout.getLayoutParams();
                lp.leftMargin = left + (int) animation.getAnimatedValue();
                lp.rightMargin = right + (int) animation.getAnimatedValue();
                lp.topMargin = top - (int) animation.getAnimatedValue()-80;
                frmLayout.setLayoutParams(lp);
            }
        });
        va.start();
    }

    @Override
    public void onFinishClicked(Date date) {
        showOkDialog("", DateUtils.formatFullDatePeriods(date), null);
    }
}
