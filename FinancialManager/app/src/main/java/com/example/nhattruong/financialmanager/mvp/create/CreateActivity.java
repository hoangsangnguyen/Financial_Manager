package com.example.nhattruong.financialmanager.mvp.create;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.detail.SpinnerStateAdapter;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.create.adapter.CalculatorAdapter;
import com.example.nhattruong.financialmanager.mvp.create.adapter.CategoryAdapter;
import com.example.nhattruong.financialmanager.mvp.create.fragment.DetailsFragment;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CreateActivity extends BaseActivity implements View.OnClickListener, CreateContract.View, DetailsFragment.ICalendarListener {

    public static final String CREATE_TYPE = "CREATE_TYPE";

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

    @BindView(R.id.ll_calculator)
    LinearLayout llCalculator;

    @BindView(R.id.ll_category)
    LinearLayout llCategory;

    @BindView(R.id.ll_details)
    LinearLayout llDetail;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_type_create)
    TextView tvTypeCreate;

    @BindView(R.id.tv_currency)
    TextView tvCurrency;

    @BindView(R.id.tv_next)
    TextView tvNext;

    @BindView(R.id.tv_details)
    TextView tvDetail;

    @BindView(R.id.rcv_calculator)
    RecyclerView rcvCalculator;

    @BindView(R.id.rcv_category)
    RecyclerView rcvCategory;

    @BindView(R.id.tv_state_next)
    TextView tvStateNext;

    @BindView(R.id.edt_origin)
    TextInputLayout edtOrigin;

    @BindView(R.id.rd_negative_debt)
    RadioButton rdNegative;

    @BindView(R.id.rd_positive_debt)
    RadioButton rdPositive;

    @BindView(R.id.ll_state_debt)
    LinearLayout llStateDebt;

    @BindView(R.id.tv_state_display)
    TextView tvDisplayState;

    @BindView(R.id.sp_state)
    Spinner spState;

    private SpinnerStateAdapter spinnerStateAdapter;
    private CategoryAdapter categoryAdapter;
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new CreatePresenter());
        setContentView(R.layout.activity_create_income_layout);
        super.onCreate(savedInstanceState);

        initData();
        initListener();
    }

    @Override
    public CreatePresenter getPresenter() {
        return (CreatePresenter) super.getPresenter();
    }

    private void initData() {

        mType = getIntent().getIntExtra(CREATE_TYPE, 0);

        tvDisplayState.setText(getString(R.string.ready));
        spinnerStateAdapter = new SpinnerStateAdapter(this, Arrays.asList(getResources().getStringArray(R.array.list_state_debt)), new SpinnerStateAdapter.ISpinnerCallback() {
            @Override
            public void onItemSelected(String state) {
                tvDisplayState.setText(state);
                hideSpinnerDropDown();
            }
        });
        spState.setAdapter(spinnerStateAdapter);

        setupTextTypeCreate();
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
        numberList.add(-1);
        numberList.add(0);
        numberList.add(R.drawable.ic_keyboard_delete);

        rcvCalculator.setLayoutManager(new GridLayoutManager(this, 3));
        rcvCalculator.setHasFixedSize(true);
        rcvCalculator.setAdapter(new CalculatorAdapter(this, numberList, new CalculatorAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {

                String currency = tvCurrency.getText().toString();
                if (position >= numberList.size() - 1 && position != 9) {
                    if (!TextUtils.isEmpty(currency)) {
                        if (currency.length() == 1) {
                            tvCurrency.setText("");
                        } else {
                            currency = currency.substring(0, currency.length() - 1);
                            tvCurrency.setText(currency);
                        }
                    }
                } else if (position == 10 && !TextUtils.isEmpty(tvCurrency.getText())) {
                    tvCurrency.setText(currency.concat(String.valueOf(0)));
                } else if (position == 9) {
                    tvCurrency.setText(currency.concat("."));
                } else {
                    tvCurrency.setText(currency.concat(String.valueOf(position + 1)));
                }
            }
        }));

        categoryAdapter = new CategoryAdapter(this, getPresenter().getJarList(), new CategoryAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(String id) {
                getPresenter().setJarId(id);
            }
        });
        rcvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        rcvCategory.setAdapter(categoryAdapter);

        initFragmentDetail();

        getPresenter().getAllJar();
    }

    private void initFragmentDetail() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailsFragment detailsFragment = DetailsFragment.newInstance();
        fragmentTransaction.add(R.id.ll_details, detailsFragment);
        fragmentTransaction.commit();

    }

    private void initListener() {
        ivLeftBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        tvDetail.setOnClickListener(this);
        tvStateNext.setOnClickListener(this);
        tvDisplayState.setOnClickListener(this);
    }

    private void hideSpinnerDropDown() {
        try {
            Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
            method.setAccessible(true);
            method.invoke(spState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvNext) {
            if (mType != AppConstants.CREATE_GENERAL) {
                llCalculator.setVisibility(View.GONE);
                llCategory.setVisibility(View.VISIBLE);
//                llInOutCome.setVisibility(View.GONE);
                tvTypeCreate.setVisibility(View.GONE);
            } else {
                llDetail.setVisibility(View.VISIBLE);
//                llInOutCome.setVisibility(View.GONE);
                tvTypeCreate.setVisibility(View.GONE);
                llCalculator.setVisibility(View.GONE);
            }
            showCategoryView();

        } else if (view == ivLeftBack) {
            onBackPressed();
        } else if (view == tvDetail) {
            if (mType == AppConstants.CREATE_DEBT) {
                llStateDebt.setVisibility(View.VISIBLE);
            } else {
                llDetail.setVisibility(View.VISIBLE);
            }
            rcvCategory.setVisibility(View.INVISIBLE);
            tvDetail.setOnClickListener(null);
            animation(llCategory);
        } else if (view == tvStateNext) {
            edtOrigin.setVisibility(View.INVISIBLE);

            llDetail.setVisibility(View.VISIBLE);
            tvStateNext.setOnClickListener(null);
            animation(llStateDebt);
        } else if (view == tvDisplayState){
            spinnerStateAdapter.setTextView(tvDisplayState);
            spState.performClick();
        }

    }

    private void setupTextTypeCreate() {
        switch (mType) {
            case AppConstants.CREATE_INCOME:
                tvTypeCreate.setText(getString(R.string.incomes));
                tvTitle.setText(getString(R.string.incomes));
                break;
            case AppConstants.CREATE_SPENDING:
                tvTypeCreate.setText(getString(R.string.spending));
                tvTitle.setText(getString(R.string.spending));
                break;
            case AppConstants.CREATE_DEBT:
                tvTypeCreate.setText(getString(R.string.debts));
                tvTitle.setText(getString(R.string.debts));
                break;
            case AppConstants.CREATE_GENERAL:
                tvTypeCreate.setText(getString(R.string.general));
                tvTitle.setText(getString(R.string.general));
                break;
        }
    }

    private void showCategoryView() {

        DisplayMetrics metrics = getResources().getDisplayMetrics();

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
                lp.topMargin = top - (int) animation.getAnimatedValue() - 80;
                frmLayout.setLayoutParams(lp);
            }
        });
        va.start();
    }

    @Override
    public void onFinishClicked(Date date, String note) {
        switch (mType) {
            case AppConstants.CREATE_INCOME:
                getPresenter().createIncomeForJar(date, note, Double.parseDouble(tvCurrency.getText().toString()));
                break;
            case AppConstants.CREATE_SPENDING:
                getPresenter().createSpending(date, note,  Double.parseDouble(tvCurrency.getText().toString()));
                break;
            case AppConstants.CREATE_DEBT:
                String origin = edtOrigin.getEditText().getText().toString();
                String state = tvDisplayState.getText().toString();
                boolean isPositive = rdPositive.isChecked();
                getPresenter().createDebt(date, note, Double.parseDouble(tvCurrency.getText().toString()), origin, state, isPositive);
                break;
            case AppConstants.CREATE_GENERAL:
                getPresenter().createGeneralIncome(date, note, Double.parseDouble(tvCurrency.getText().toString()));
                break;
        }
    }

    @Override
    public void getAllJarSuccess() {
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void createSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
