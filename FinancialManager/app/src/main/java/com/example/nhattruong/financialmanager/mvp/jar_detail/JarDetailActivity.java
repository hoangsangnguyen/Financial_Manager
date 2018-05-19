package com.example.nhattruong.financialmanager.mvp.jar_detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.dialogDateFromToPicker.DateFromToPickerDialog;
import com.example.nhattruong.financialmanager.mvp.jar_detail.adapter.JarDetailPagerAdapter;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.DebtFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income.IncomeFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending.SpendingFragment;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class JarDetailActivity extends BaseActivity {

    private static final int TYPE_INCOME = 0;
    private static final int TYPE_SPENDING = 1;
    private static final int TYPE_DEBT = 2;

    @BindView(R.id.ln_left)
    LinearLayout lnLeft;

    @BindView(R.id.tv_title_top_bar)
    TextView tvTitle;

    @BindView(R.id.vp_detail)
    ViewPager vpDetail;

    @BindView(R.id.sliding_tabs)
    TabLayout tabSliding;

    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    @BindView(R.id.tv_end_date)
    TextView tvEndDate;

    @BindView(R.id.iv_calendar_filter_detail)
    ImageView ivCalendar;

    private List<BaseJarDetailFragment> mFragments = new ArrayList<>();
    private String jarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new JarDetailPresenter());
        setContentView(R.layout.activity_detail_jar);
        super.onCreate(savedInstanceState);
    }

    @Override
    public JarDetailPresenter getPresenter() {
        return (JarDetailPresenter) super.getPresenter();
    }

    @Override
    public void onInitData() {

        jarId = getIntent().getStringExtra(AppConstants.JAR_ID);

        tvTitle.setText(getString(R.string.detail));

        // init fragments
        mFragments.add(IncomeFragment.newInstance(jarId));
        mFragments.add(SpendingFragment.newInstance(jarId));
        mFragments.add(DebtFragment.newInstance(jarId));

        // init view pager
        vpDetail.setAdapter(new JarDetailPagerAdapter(this, getSupportFragmentManager(), mFragments));
        tabSliding.setupWithViewPager(vpDetail);
    }

    @Override
    public void onInitListener() {
        vpDetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              if (getPresenter().getDateStart() != null && getPresenter().getDateEnd() != null){
                  filterDetail();
              }else {
                  loadData(position);
              }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        lnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFromToPickerDialog dialog = new DateFromToPickerDialog(
                        JarDetailActivity.this,
                        getPresenter().getDateStart(),
                        getPresenter().getDateEnd(),
                        new DateFromToPickerDialog.OnClickDoneListener() {
                            @Override
                            public void onDoneClick(Date dateFrom, Date dateTo) {
                                tvStartDate.setText(DateUtils.formatFullDatePeriods(dateFrom));
                                tvEndDate.setText(DateUtils.formatFullDatePeriods(dateTo));
                                getPresenter().setDateStart(dateFrom);
                                getPresenter().setDateEnd(dateTo);
                                filterDetail();
                            }
                        });
                dialog.show();
            }
        });
    }

    private void filterDetail() {
        Date dateFrom = getPresenter().getDateStart();
        Date dateTo = getPresenter().getDateEnd();
        switch (vpDetail.getCurrentItem()){
            case TYPE_SPENDING:
                ((SpendingFragment) mFragments.get(TYPE_SPENDING)).filterSpending(dateFrom, dateTo);
                break;
            case TYPE_INCOME:
                ((IncomeFragment) mFragments.get(TYPE_INCOME)).filterIncome(dateFrom, dateTo);
                break;
            case TYPE_DEBT:
                ((DebtFragment) mFragments.get(TYPE_DEBT)).filterDebt(dateFrom, dateTo);
                break;
        }
    }

    private void loadData(int position){
        switch (position) {
            case TYPE_SPENDING:
                ((SpendingFragment) mFragments.get(TYPE_SPENDING)).getAllSpending();
                break;
            case TYPE_INCOME:
                ((IncomeFragment) mFragments.get(TYPE_INCOME)).getIncomes();
                break;
            case TYPE_DEBT:
                ((DebtFragment) mFragments.get(TYPE_DEBT)).getAllDebt(jarId);
                break;
        }
    }

}
