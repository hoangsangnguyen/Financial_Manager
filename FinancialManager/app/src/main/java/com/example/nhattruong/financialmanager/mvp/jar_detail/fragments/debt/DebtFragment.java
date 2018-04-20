package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ExpandableListView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter.JarDetailExpandableAdapter;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import butterknife.BindView;

public class DebtFragment extends BaseJarDetailFragment implements DebtContract.View {

    @BindView(R.id.lv_detail_jar)
    ExpandableListView lvDetail;

    @BindView(R.id.refresh_detail)
    SwipeRefreshLayout mRefresh;

    private JarDetailExpandableAdapter adapter;

    public static DebtFragment newInstance(String jarId) {
        Bundle args = new Bundle();
        args.putString(AppConstants.JAR_ID, jarId);
        DebtFragment fragment = new DebtFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public DebtPresenter getPresenter() {
        return (DebtPresenter) super.getPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new DebtPresenter());
    }

    @Override
    protected void onInitData() {
        getPresenter().setJarId(getArguments().getString(AppConstants.JAR_ID));

        adapter = new JarDetailExpandableAdapter(getActivity(), getPresenter().getList());
        lvDetail.setAdapter(adapter);

        getPresenter().getAllDebt();
    }

    @Override
    protected void onInitListener() {

        adapter.setItemDebtListener(new JarDetailExpandableAdapter.OnItemDebtListener() {
            @Override
            public void onEditClicked(int position) {
                //edit debt
            }

            @Override
            public void onDeleteClicked(int position) {
                //delete debt
            }
        });

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(false);
                getPresenter().getAllDebt();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jar_detail;
    }

    @Override
    public void getAllDebtSuccess() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getAllDebtFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void getAllDebt(String jarId) {
        getPresenter().setJarId(jarId);
        getPresenter().getAllDebt();
    }
}
