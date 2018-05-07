package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.IncomeResponse;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto.JarDetailDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.income.dto.IncomeDTO;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class IncomePresenter extends BasePresenter implements IncomeContract.Presenter{

    private List<JarDetailDTO> mList;
    private String mJarId;
    private Date dateFrom, dateTo;

    public String getJarId() {
        return mJarId;
    }

    public void setJarId(String jarId) {
        this.mJarId = jarId;
    }

    public void setDateFromTo(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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
    public void getIncomes() {
        if (dateFrom != null && dateTo !=null){
            filterIncome();
        } else {
            getAllIncome();
        }
    }

    private void getAllIncome() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().getAllIncome(getSQLiteManager().getUser().getId(), mJarId, new ApiCallback<IncomeResponse>() {
            @Override
            public void success(IncomeResponse res) {
                getListIncome().clear();
                parseToJarDetailDTO(res.getIncomes());
                Collections.sort(mList);
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

    private void filterIncome(){
        if (!isViewAttached()) return;
        getView().showLoading();

        String dateFromString = DateUtils.formatDateFilter(dateFrom);
        String dateToString = DateUtils.formatDateFilter(dateTo);

        getApiManager().filterIncome(getSQLiteManager().getUser().getId(), mJarId,dateFromString, dateToString, new ApiCallback<IncomeResponse>() {
            @Override
            public void success(IncomeResponse res) {
                getListIncome().clear();
                parseToJarDetailDTO(res.getIncomes());
                Collections.sort(mList);
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
                if (DateUtils.compareDate(startIncomeDTO.getDate(), incomeDTOs.get(i).getDate())==0) {
                    listChildIncomeDTO.add(incomeDTOs.get(i));
                    incomeDTOs.remove(i);
                }
            }

            mList.add(new JarDetailDTO(startIncomeDTO.getDate(), listChildIncomeDTO));
        }
    }
}
