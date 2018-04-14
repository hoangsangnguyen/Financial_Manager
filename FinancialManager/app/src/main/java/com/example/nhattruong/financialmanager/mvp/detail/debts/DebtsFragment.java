package com.example.nhattruong.financialmanager.mvp.detail.debts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.DateDebts;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;
import com.example.nhattruong.financialmanager.mvp.detail.MainDetailApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class DebtsFragment extends Fragment implements IDetailInteractor.IViewDebts {

    @BindView(R.id.pb_waitDebts)
    ProgressBar pbWaitDebts;
    @BindView(R.id.elv_debts)
    ExpandableListView elvDebts;

    private DebtsPresenter debtsPresenter;
    private ApiServices apiServices;

    public static DebtsFragment newInstance() {
        return new DebtsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debts, container, false);
        ButterKnife.bind(this, view);

        Retrofit retrofit = MainDetailApplication.getRetrofit();
        debtsPresenter = new DebtsPresenter(this);
        apiServices = retrofit.create(ApiServices.class);

        pbWaitDebts.setVisibility(View.VISIBLE);

        loadDebtsData();
        return view;
    }

    @Override
    public void showSuccess(List<DateDebts> dateDebtsList) {
        pbWaitDebts.setVisibility(View.GONE);
        showDebtsData(dateDebtsList);
    }

    @Override
    public void showFailed() {
        pbWaitDebts.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Show failure", Toast.LENGTH_SHORT).show();
    }

    private void loadDebtsData() {
        debtsPresenter.callDebtsData(apiServices);
    }

    private void showDebtsData(List<DateDebts> dateDebtsList) {
        DebtsExpandableListViewAdapter debtsExpandableListViewAdapter = new DebtsExpandableListViewAdapter(getActivity(), dateDebtsList);
        elvDebts.setAdapter(debtsExpandableListViewAdapter);
        debtsExpandableListViewAdapter.notifyDataSetChanged();
    }
}
