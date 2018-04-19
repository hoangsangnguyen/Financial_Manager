package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ExpandableListView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import butterknife.BindView;

public class IncomeFragment extends BaseJarDetailFragment implements IncomeContract.View {

    @BindView(R.id.lv_detail_jar)
    ExpandableListView rcvDetail;

    @BindView(R.id.refresh_detail)
    SwipeRefreshLayout mRefresh;

    public static IncomeFragment newInstance(String jarId) {
        Bundle args = new Bundle();
        args.putString(AppConstants.JAR_ID, jarId);
        IncomeFragment fragment = new IncomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new IncomePresenter());
    }

    @Override
    public IncomePresenter getPresenter() {
        return (IncomePresenter)super.getPresenter();
    }

    @Override
    protected void onInitData() {
        getPresenter().setJarId(getArguments().getString(AppConstants.JAR_ID));
    }

    @Override
    protected void onInitListener() {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(false);
                getPresenter().getAllIncome();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jar_detail;
    }

    @Override
    public void getIncomeSuccess() {

    }

    @Override
    public void getIncomeFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void getAllIncome(String jarId) {
        getPresenter().setJarId(jarId);
        getPresenter().getAllIncome();
    }
}
