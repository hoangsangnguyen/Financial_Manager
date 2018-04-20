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

    private void parseToJarDetailDTO(List<Income> listSpending) {
        List<IncomeDTO> incomeDTOs = new ArrayList<>();
        for (Income item : listSpending) {
            incomeDTOs.add(new IncomeDTO(item));
        }

        while ((incomeDTOs.size() - 1) >= 0) {
            List<IJarDetail> listChildIncomeDTO = new ArrayList<>();
            listChildIncomeDTO.add(incomeDTOs.get(0));

            IncomeDTO startIncomeDTO = incomeDTOs.get(0);
            incomeDTOs.remove(0);

            for (int i = incomeDTOs.size() - 1; i >=0; i--) {
                if (compareDate(startIncomeDTO.getDate(), incomeDTOs.get(i).getDate())) {
                    listChildIncomeDTO.add(incomeDTOs.get(i));
                    incomeDTOs.remove(i);
                }
            }

            mList.add(new JarDetailDTO(startIncomeDTO.getDate(), listChildIncomeDTO));
        }
    }

    private boolean compareDate(String date1, String date2){
        date1 = date1.substring(0, date1.indexOf("T"));
        date2 = date2.substring(0, date2.indexOf("T"));

        return date1.equalsIgnoreCase(date2);
    }
}
