package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.income.CreateIncomeActivity;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter.JarDetailExpandableAdapter;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

public class IncomeFragment extends BaseJarDetailFragment implements IncomeContract.View {

    @BindView(R.id.lv_detail_jar)
    ExpandableListView rcvDetail;

    @BindView(R.id.fab_add_item)
    FloatingActionButton fabAdd;

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
        return (IncomePresenter)super.getPresenter();
    }

    @Override
    protected void onInitData() {
        getPresenter().setJarId(getArguments().getString(AppConstants.JAR_ID));

        adapter = new JarDetailExpandableAdapter(getActivity(), getPresenter().getListIncome());
        rcvDetail.setAdapter(adapter);

        getPresenter().getAllIncome();
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

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCreate = new Intent(getActivity(), CreateIncomeActivity.class);
                intentCreate.putExtra(CreateIncomeActivity.CREATE_TYPE, AppConstants.CREATE_INCOME);
                startActivityForResult(intentCreate, AppConstants.REQUEST_CODE_CREATE);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jar_detail;
    }

    @Override
    public void getIncomeSuccess() {
        adapter.notifyDataSetChanged();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.REQUEST_CODE_CREATE && resultCode == RESULT_OK){
            getPresenter().getAllIncome();
        }
    }
}
