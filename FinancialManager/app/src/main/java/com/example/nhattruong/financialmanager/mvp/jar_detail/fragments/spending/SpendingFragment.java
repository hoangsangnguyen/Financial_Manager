package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.dialog.DialogPositiveNegative;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter.JarDetailExpandableAdapter;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import java.util.Date;

import butterknife.BindView;

public class SpendingFragment extends BaseJarDetailFragment implements SpendingContract.View {

    @BindView(R.id.tv_no_data)
    TextView tvNoData;

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
        return (SpendingPresenter) super.getPresenter();
    }

    @Override
    protected void onInitData() {
        getPresenter().setJarId(getArguments().getString(AppConstants.JAR_ID));

        adapter = new JarDetailExpandableAdapter(getActivity(), getPresenter().getListSpending());
        lvDetail.setAdapter(adapter);

        getPresenter().getSpending();
    }

    @Override
    protected void onInitListener() {
        adapter.setItemSpendingListener(new JarDetailExpandableAdapter.OnItemSpendingListener() {
            @Override
            public void onDeleteClicked(final int positionGroup, final int positionChild) {
                // delete spending
                showConfirmDialog("Are you sure to delete this?", new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        getPresenter().deleteSpending(positionGroup, positionChild);
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                    }
                });
            }
        });

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(false);
                getPresenter().getSpending();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jar_detail;
    }

    @Override
    public void getSpendingSuccess() {
        tvNoData.setVisibility(getPresenter().getListSpending().isEmpty()? View.VISIBLE: View.GONE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void getAllSpending() {
        getPresenter().getSpending();
    }

    @Override
    public void filterSpending(Date dateStart, Date dateEnd) {
        getPresenter().setDateFromTo(dateStart, dateEnd);
        getPresenter().getSpending();
    }

    @Override
    public void deleteSpendingSuccess() {
        adapter.notifyDataSetChanged();
    }
}
