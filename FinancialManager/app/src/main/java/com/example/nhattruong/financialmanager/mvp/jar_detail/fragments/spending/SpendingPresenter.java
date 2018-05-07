package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.SpendingResponse;
import com.example.nhattruong.financialmanager.model.Spending;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto.JarDetailDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.spending.dto.SpendingDTO;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpendingPresenter extends BasePresenter implements SpendingContract.Presenter{

    private List<JarDetailDTO> mList;
    private String mJarId;
    private Date dateFrom, dateTo;

    public String getJarId() {
        return mJarId;
    }

    public void setJarId(String jarId) {
        this.mJarId = jarId;
    }

    public List<JarDetailDTO> getListSpending(){
        if (mList == null){
            mList = new ArrayList<>();
        }
        return mList;
    }

    public void setDateFromTo(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public SpendingContract.View getView() {
        return (SpendingContract.View)super.getView();
    }

    @Override
    public void getSpending() {
        if (dateFrom == null && dateTo == null){
            getAllSpending();
        } else {
            filterSpending();
        }
    }

    private void getAllSpending() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().getAllSpending(getSQLiteManager().getUser().getId(), mJarId, new ApiCallback<SpendingResponse>() {
            @Override
            public void success(SpendingResponse res) {
                getListSpending().clear();
                parseToJarDetailDTO(res.getSpendings());
//                Collections.sort(mList);
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().getSpendingSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().onFailure(error);
            }
        });
    }

    private void filterSpending(){
        if (!isViewAttached()) return;
        getView().showLoading();

        String dateFromString = DateUtils.formatDateFilter(dateFrom);
        String dateToString = DateUtils.formatDateFilter(dateTo);

        getApiManager().filterSpending(
                getSQLiteManager().getUser().getId(),
                mJarId,
                dateFromString,
                dateToString,
                new ApiCallback<SpendingResponse>() {
                    @Override
                    public void success(SpendingResponse res) {
                        getListSpending().clear();
                        parseToJarDetailDTO(res.getSpendings());
//                Collections.sort(mList);
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getView().getSpendingSuccess();
                    }

                    @Override
                    public void failure(RestError error) {
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getView().onFailure(error);
                    }
                });
    }

    @Override
    public void deleteSpending(final int positionGroup, final int positionChild) {
        if (!isViewAttached()) return;
        getView().showLoading();
        
        getApiManager().deleteSpending(
                getSQLiteManager().getUser().getId(),
                mJarId,
                getListSpending().get(positionGroup).getList().get(positionChild).getId(),
                new ApiCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        getListSpending().get(positionGroup).getList().remove(positionChild);
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getAllSpending();
//                        getView().deleteSpendingSuccess();
                    }

                    @Override
                    public void failure(RestError error) {
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getView().onFailure(error);
                    }
                }
        );
    }

    private void parseToJarDetailDTO(List<Spending> spending) {
        List<SpendingDTO> spendingDTOS = new ArrayList<>();
        for (Spending item : spending) {
            spendingDTOS.add(new SpendingDTO(item));
        }

        while ((spendingDTOS.size() - 1) >= 0) {
            List<IJarDetail> listChildSpendingDTO = new ArrayList<>();
            listChildSpendingDTO.add(spendingDTOS.get(0));

            SpendingDTO newSpendingDTO = spendingDTOS.get(0);
            spendingDTOS.remove(0);

            for (int i = spendingDTOS.size() - 1; i >=0; i--) {
                if (compareDate(newSpendingDTO.getDate().toString(), spendingDTOS.get(i).getDate().toString())) {
                    listChildSpendingDTO.add(spendingDTOS.get(i));
                    spendingDTOS.remove(i);
                }
            }

            mList.add(new JarDetailDTO(newSpendingDTO.getDate(), listChildSpendingDTO));
        }
    }

    private boolean compareDate(String date1, String date2){
        date1 = date1.substring(0, date1.indexOf("T"));
        date2 = date2.substring(0, date2.indexOf("T"));

        return date1.equalsIgnoreCase(date2);
//        return date1.compareTo(date2) == 0;
    }
}
