package com.example.nhattruong.financialmanager.mvp.detail.incomes;

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
import com.example.nhattruong.financialmanager.model.DateIncomes;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;
import com.example.nhattruong.financialmanager.mvp.detail.MainDetailApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class IncomesFragment extends Fragment implements IDetailInteractor.IViewIncomes {

    @BindView(R.id.pb_waitIncomes)
    ProgressBar pbWaitIncomes;
    @BindView(R.id.elv_incomes)
    ExpandableListView elvIncomes;

    private IncomesPresenter incomesPresenter;
    private ApiServices apiServices;

    public static IncomesFragment newInstance() {
        return new IncomesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_incomes, container, false);
        ButterKnife.bind(this, view);

        Retrofit retrofit = MainDetailApplication.getRetrofit();
        apiServices = retrofit.create(ApiServices.class);
        incomesPresenter = new IncomesPresenter(this);

        pbWaitIncomes.setVisibility(View.VISIBLE);
        loadIncomesData();
        return view;
    }

    @Override
    public void showSuccess(List<DateIncomes> dateIncomesList) {
        pbWaitIncomes.setVisibility(View.GONE);
        showIncomesData(dateIncomesList);
    }

    @Override
    public void showFailed() {
        pbWaitIncomes.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Show failure", Toast.LENGTH_SHORT).show();
    }

    private void loadIncomesData() {
        incomesPresenter.callIncomesData(apiServices);
    }

    private void showIncomesData(List<DateIncomes> dateIncomesList) {
        IncomesExpandableListViewAdapter incomesExpandableListViewAdapter = new IncomesExpandableListViewAdapter(getActivity(), dateIncomesList);
        elvIncomes.setAdapter(incomesExpandableListViewAdapter);
        incomesExpandableListViewAdapter.notifyDataSetChanged();
    }

}
