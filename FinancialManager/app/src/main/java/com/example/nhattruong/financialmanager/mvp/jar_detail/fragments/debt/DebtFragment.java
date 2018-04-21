package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ExpandableListView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.dialog.detail.EditDebtDialog;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.Debt;
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

        adapter = new JarDetailExpandableAdapter(getActivity(), getPresenter().getListDebt());
        lvDetail.setAdapter(adapter);

        getPresenter().getAllDebt();
    }

    @Override
    protected void onInitListener() {

        adapter.setItemDebtListener(new JarDetailExpandableAdapter.OnItemDebtListener() {
            @Override
            public void onEditClicked(int positionGroup, int positionChild) {

               getPresenter().setPositionGroupAndChild(positionGroup, positionChild);

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
            public void onDeleteClicked(int positionGroup, int positionChild) {
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
    public void onSuccess() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(RestError error) {
        showErrorDialog(error.message);
    }

    @Override
    public void getAllDebt(String jarId) {
        getPresenter().setJarId(jarId);
        getPresenter().getAllDebt();
    }

}
