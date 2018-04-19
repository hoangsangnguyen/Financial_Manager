package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.SpendingResponse;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto.JarDetailDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income.dto.IncomeDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending.dto.SpendingDTO;

import java.util.ArrayList;
import java.util.List;

public class IncomePresenter extends BasePresenter implements IncomeContract.Presenter{

    private List<JarDetailDTO> mList;
    private String mJarId;

    public String getJarId() {
        return mJarId;
    }

    public void setJarId(String jarId) {
        this.mJarId = jarId;
    }

    public List<JarDetailDTO> getListIncome(){
        if (mList == null){
            mList = new ArrayList<>();
        }
        return mList;
    }

    @Override
    public IncomeContract.View getView() {
        return (IncomeContract.View)super.getView();
    }

    @Override
    public void getAllIncome() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().getAllIncome(getPreferManager().getUser().getId(), mJarId, new ApiCallback<IncomeResponse>() {
            @Override
            public void success(IncomeResponse res) {
                getListIncome().clear();
                parseToJarDetailDTO(res.getIncomes());
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().getIncomeSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().getIncomeFailure(error);
            }
        });
    }

    private void parseToJarDetailDTO(List<Income> spending) {
        List<IncomeDTO> incomeDTOs = new ArrayList<>();
        for (Income item : spending) {
            incomeDTOs.add(new IncomeDTO(item));
        }

        while ((incomeDTOs.size() - 1) >= 0) {
            List<IJarDetail> listChildIncomeDTO = new ArrayList<>();
            listChildIncomeDTO.add(incomeDTOs.get(0));

            IncomeDTO newSpendingDTO = incomeDTOs.get(0);

            for (int i = 1; i <= incomeDTOs.size() - 1; i++) {
                if (newSpendingDTO.getDate().compareTo(incomeDTOs.get(i).getDate()) == 0) {
                    listChildIncomeDTO.add(incomeDTOs.get(i));
                    i--;
                }
            }

            mList.add(new JarDetailDTO(newSpendingDTO.getDate(), listChildIncomeDTO));
        }
    }
}
