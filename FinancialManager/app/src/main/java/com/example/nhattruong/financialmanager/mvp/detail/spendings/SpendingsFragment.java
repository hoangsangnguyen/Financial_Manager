package com.example.nhattruong.financialmanager.mvp.detail.spendings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiServices;
import com.example.nhattruong.financialmanager.model.DateSpendings;
import com.example.nhattruong.financialmanager.mvp.detail.IDetailInteractor;
import com.example.nhattruong.financialmanager.mvp.detail.MainDetailApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class SpendingsFragment extends Fragment implements IDetailInteractor.IViewSpendings {

    @BindView(R.id.elv_spendings)
    ExpandableListView elvSpendings;
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

        pbWaitSpendings.setVisibility(View.VISIBLE);

        Bundle bundle = getActivity().getIntent().getExtras();

        getInfoId(bundle);

        return view;
    }

    @Override
    public void getIdSuccess(String token, String userId, String jarId) {
        spendingsPresenter.callSpendingsData(apiServices, token, userId, jarId);
    }

    @Override
    public void getIdFailure() {
        pbWaitSpendings.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Show Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(List<DateSpendings> dateSpendingsList) {
        pbWaitSpendings.setVisibility(View.GONE);
        if (dateSpendingsList.isEmpty()) {
            Toast.makeText(getActivity(), "NOT DATA", Toast.LENGTH_SHORT).show();
        } else {
            showSpendingsData(dateSpendingsList);
        }
    }

    @Override
    public void showFailed() {
        pbWaitSpendings.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Show Failure", Toast.LENGTH_SHORT).show();
    }

    private void showSpendingsData(List<DateSpendings> dateSpendingsList) {
        SpendingsExpandableListViewAdapter spendingsExpandableListViewAdapter = new SpendingsExpandableListViewAdapter(getActivity(), dateSpendingsList);
        elvSpendings.setAdapter(spendingsExpandableListViewAdapter);
        spendingsExpandableListViewAdapter.notifyDataSetChanged();
    }

    private void getInfoId(Bundle bundle) {
        spendingsPresenter.callInfoId(bundle);
    }
}
