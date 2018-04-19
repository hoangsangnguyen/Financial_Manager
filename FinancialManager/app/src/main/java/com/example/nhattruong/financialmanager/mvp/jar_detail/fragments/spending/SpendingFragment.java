package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.example.nhattruong.financialmanager.mvp.jar_detail.adapter.JarDetailPagerAdapter;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter.JarDetailExpandableAdapter;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import butterknife.BindView;

public class SpendingFragment extends BaseJarDetailFragment implements SpendingContract.View{

    @BindView(R.id.lv_detail_jar)
    ExpandableListView lvDetail;

    @BindView(R.id.refresh_detail)
    SwipeRefreshLayout mRefresh;

    private JarDetailExpandableAdapter adapter;

    public static SpendingFragment newInstance(String jarId) {
        Bundle args = new Bundle();
        args.putString(AppConstants.JAR_ID, jarId);
        SpendingFragment fragment = new SpendingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new SpendingPresenter());
    }

    @Override
    public SpendingPresenter getPresenter() {
        return (SpendingPresenter)super.getPresenter();
    }

    @Override
    protected void onInitData() {
        getPresenter().setJarId(getArguments().getString(AppConstants.JAR_ID));

        adapter = new JarDetailExpandableAdapter(getActivity(), getPresenter().getListSpending());
        lvDetail.setAdapter(adapter);

        getPresenter().getAllSpending();
    }

    @Override
    protected void onInitListener() {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(false);
                getPresenter().getAllSpending();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jar_detail;
    }

    @Override
    public void getSpendingSuccess() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getSpendingFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void getAllSpending(String jarId) {
        getPresenter().setJarId(jarId);
        getPresenter().getAllSpending();
    }
}
