package com.example.nhattruong.financialmanager.mvp.jar_detail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.custom_view.CenterLayoutManager;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.mvp.jar_detail.adapter.JarDetailPagerAdapter;
import com.example.nhattruong.financialmanager.mvp.jar_detail.adapter.JarDetailTabHeaderAdapter;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.DebtFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income.IncomeFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending.SpendingFragment;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

public class JarDetailActivity extends BaseActivity implements JarDetailTabHeaderAdapter.HeaderAdapterListener {

    private static final int TYPE_INCOME = 0;
    private static final int TYPE_SPENDING = 1;
    private static final int TYPE_DEBT = 2;

    @BindView(R.id.ln_left)
    LinearLayout lnLeft;

    @BindView(R.id.tv_title_top_bar)
    TextView tvTitle;

    @BindView(R.id.rcv_header)
    RecyclerView rcvHeader;

    @BindView(R.id.vp_detail)
    ViewPager vpDetail;

    @BindView(R.id.v_indicator)
    CircleIndicator vIndicator;

    private JarDetailTabHeaderAdapter mTabAdapter;
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

        // init fragments
        mFragments.add(IncomeFragment.newInstance(jarId));
        mFragments.add(SpendingFragment.newInstance(jarId));
        mFragments.add(DebtFragment.newInstance(jarId));

        mTabAdapter = new JarDetailTabHeaderAdapter(this, getPresenter().getListTabHeader(), this);
        rcvHeader.setAdapter(mTabAdapter);
        rcvHeader.setLayoutManager(new CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // init view pager
        vpDetail.setAdapter(new JarDetailPagerAdapter(getSupportFragmentManager(), mFragments));
        vIndicator.setViewPager(vpDetail);
    }

    @Override
    public void onInitListener() {
        vpDetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabAdapter.setSelectedTab(position);
                mTabAdapter.notifyDataSetChanged();
                rcvHeader.smoothScrollToPosition(position);
                loadData(position);
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
    }

    @Override
    public void onTabSelected(int position) {
        mTabAdapter.setSelectedTab(position);
        mTabAdapter.notifyDataSetChanged();
        vpDetail.setCurrentItem(position);
        updateTabHeader(position);
        loadData(position);
    }

    private void loadData(int position){
        switch (position) {
            case TYPE_SPENDING:
                ((SpendingFragment) mFragments.get(TYPE_SPENDING)).getAllSpending(jarId);
                break;
            case TYPE_INCOME:
                ((IncomeFragment) mFragments.get(TYPE_INCOME)).getAllIncome(jarId);
                break;
            case TYPE_DEBT:
                ((DebtFragment) mFragments.get(TYPE_DEBT)).getAllDebt(jarId);
        }
    }

    private void updateTabHeader(int position) {
        switch (position) {
            case TYPE_SPENDING:
                tvTitle.setText(getString(R.string.spending));
                break;
            case TYPE_INCOME:
                tvTitle.setText(getString(R.string.incomes));
                break;
            case TYPE_DEBT:
                tvTitle.setText(getString(R.string.debts));
                break;
        }
    }
}
