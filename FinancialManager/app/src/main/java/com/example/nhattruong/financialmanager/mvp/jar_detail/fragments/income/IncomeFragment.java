package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter.JarDetailExpandableAdapter;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import java.util.Date;

import butterknife.BindView;

public class IncomeFragment extends BaseJarDetailFragment implements IncomeContract.View {

    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    @BindView(R.id.lv_detail_jar)
    ExpandableListView rcvDetail;

    @BindView(R.id.refresh_detail)
    SwipeRefreshLayout mRefresh;

    private JarDetailExpandableAdapter adapter;

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
        return (IncomePresenter) super.getPresenter();
    }

    @Override
    protected void onInitData() {
        getPresenter().setJarId(getArguments().getString(AppConstants.JAR_ID));

        adapter = new JarDetailExpandableAdapter(getActivity(), getPresenter().getListIncome());
        rcvDetail.setAdapter(adapter);

        getPresenter().getIncomes();
    }

    @Override
    protected void onInitListener() {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(false);
                getPresenter().getIncomes();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jar_detail;
    }

    @Override
    public void getIncomeSuccess() {
        tvNoData.setVisibility(getPresenter().getListIncome().isEmpty() ? View.VISIBLE : View.GONE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getIncomeFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void getIncomes() {
        getPresenter().getIncomes();
    }

    @Override
    public void filterIncome(Date dateFrom, Date dateTo) {
        getPresenter().setDateFromTo(dateFrom, dateTo);
        getPresenter().getIncomes();
    }
}
