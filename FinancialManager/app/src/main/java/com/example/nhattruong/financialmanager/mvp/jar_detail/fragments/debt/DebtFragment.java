package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.dialog.DialogPositiveNegative;
import com.example.nhattruong.financialmanager.dialog.detail.EditDebtDialog;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter.JarDetailExpandableAdapter;
import com.example.nhattruong.financialmanager.utils.AppConstants;

import java.util.Date;

import butterknife.BindView;

public class DebtFragment extends BaseJarDetailFragment implements DebtContract.View {

    @BindView(R.id.tv_no_data)
    TextView tvNoData;

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

        adapter = new JarDetailExpandableAdapter(getActivity(), getPresenter().getListDebt());
        lvDetail.setAdapter(adapter);

        getPresenter().getDebts();
    }

    @Override
    protected void onInitListener() {

        adapter.setItemDebtListener(new JarDetailExpandableAdapter.OnItemDebtListener() {
            @Override
            public void onEditClicked(int positionGroup, int positionChild) {

                //edit debt
                EditDebtDialog debtDialog = new EditDebtDialog(
                        getActivity(),
                        getPresenter().getListDebt().get(positionGroup).getList().get(positionChild),
                        getPresenter().getListDebt().get(positionGroup).getDate(),
                        new EditDebtDialog.OnEditDebtListener() {
                            @Override
                            public void onSaveChange(Debt debt) {
                                getPresenter().updateDebt(debt);
                            }
                        });
                debtDialog.show();
            }

            @Override
            public void onDeleteClicked(final int positionGroup, final int positionChild) {
                //delete debt
                showConfirmDialog("Are you sure to delete this?", new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        getPresenter().deleteDebt(positionGroup, positionChild);
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
                getPresenter().getDebts();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jar_detail;
    }

    @Override
    public void onSuccess() {
        tvNoData.setVisibility(getPresenter().getListDebt().isEmpty()?View.VISIBLE:View.GONE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void getAllDebt(String jarId) {
        getPresenter().getDebts();
    }

    @Override
    public void filterDebt(Date dateFrom, Date dateTo) {
        getPresenter().setDateFromTo(dateFrom, dateTo);
        getPresenter().getDebts();
    }
}
