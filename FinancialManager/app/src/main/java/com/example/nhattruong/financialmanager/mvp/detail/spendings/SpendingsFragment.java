package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;
import com.example.nhattruong.financialmanager.mvp.detail.MainDetailApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class SpendingsFragment extends Fragment implements IDetailInteractor.IViewSpendings {

    @BindView(R.id.rcv_spendings)
    RecyclerView rcvSpendings;
    @BindView(R.id.pb_waitSpendings)
    ProgressBar pbWaitSpendings;

    private ApiServices apiServices;

    private SpendingsPresenter spendingsPresenter;

    public static SpendingsFragment newInstance() {
        return new SpendingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spendings, container, false);
        ButterKnife.bind(this, view);

         Retrofit retrofit = MainDetailApplication.getRetrofit();
        apiServices = retrofit.create(ApiServices.class);
        spendingsPresenter = new SpendingsPresenter(this);
        rcvSpendings.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        loadSpendingsData();
        pbWaitSpendings.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void showSuccess(List<Spending> spendingList) {
        pbWaitSpendings.setVisibility(View.GONE);
        showSpendingsData(spendingList);
    }

    @Override
    public void showFailed() {
        pbWaitSpendings.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Show Failure", Toast.LENGTH_SHORT).show();
        Log.d("Spendings", "false");

    }

    private void loadSpendingsData() {
        spendingsPresenter.callSpendingsData(apiServices);
    }

    private void showSpendingsData(List<Spending> spendingList) {
        SpendingsRecyclerViewAdapter spendingsRecyclerViewAdapter = new SpendingsRecyclerViewAdapter(getActivity(), spendingList);
        rcvSpendings.setAdapter(spendingsRecyclerViewAdapter);
        spendingsRecyclerViewAdapter.notifyDataSetChanged();
    }
}
