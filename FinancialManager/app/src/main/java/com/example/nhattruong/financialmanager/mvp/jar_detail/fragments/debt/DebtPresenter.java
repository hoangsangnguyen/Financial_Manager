package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.DebtUpdateRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.DebtResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.UpdateDebtResponse;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.dto.DebtDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto.JarDetailDTO;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DebtPresenter extends BasePresenter implements DebtContract.Presenter {

    private List<JarDetailDTO> mList;
    private String mJarId;
    private Date dateFrom, dateTo;

    public void setJarId(String jarId) {
        this.mJarId = jarId;
    }

    public List<JarDetailDTO> getListDebt() {
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
    public DebtContract.View getView() {
        return (DebtContract.View)super.getView();
    }

    @Override
    public void getDebts() {
        if (dateFrom != null && dateTo != null){
            filterDebts();
        } else {
            getAllDebt();
        }
    }

    private void filterDebts() {
        if (!isViewAttached()) return;
        getView().showLoading();

        String dateFromString = DateUtils.formatDateFilter(dateFrom);
        String dateToString = DateUtils.formatDateFilter(dateTo);

        getApiManager().filterDebt(getSQLiteManager().getUser().getId(), mJarId,dateFromString, dateToString, new ApiCallback<DebtResponse>() {
            @Override
            public void success(DebtResponse res) {
                getListDebt().clear();
                parseToJarDetailDTO(res.getDebts());
                Collections.sort(mList);
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().onSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().onFailure(error);
            }
        });
    }

    private void getAllDebt() {
        if (!isViewAttached()) return;
        getView().showLoading();

        getApiManager().getAllDebt(getSQLiteManager().getUser().getId(), mJarId, new ApiCallback<DebtResponse>() {
            @Override
            public void success(DebtResponse res) {
                getListDebt().clear();
                parseToJarDetailDTO(res.getDebts());
                Collections.sort(mList);
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().onSuccess();
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
    public void deleteDebt(final int positionGroup, final int positionChild) {
        if (!isViewAttached()) return;
        getView().showLoading();

        getApiManager().deleteDebt(
                getSQLiteManager().getUser().getId(),
                mJarId,
                getListDebt().get(positionGroup).getList().get(positionChild).getId(),
                new ApiCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        getListDebt().get(positionGroup).getList().remove(positionChild);
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getAllDebt();
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

    @Override
    public void updateDebt(final Debt debt) {
        if (!isViewAttached()) return;
        getView().showLoading();

        DebtUpdateRequest request = new DebtUpdateRequest.DebtUpdateRequestBuilder()
                .setDate(debt.getDate())
                .setDetail(debt.getDetail())
                .setAmount(debt.getAmount())
                .setOrigin(debt.getOrigin())
                .setState(debt.getState())
                .setPositive(debt.isPositive())
                .build();

        getApiManager().updateDebt(
                getSQLiteManager().getUser().getId(),
                mJarId,
                debt.getId(),
                request,
                new ApiCallback<UpdateDebtResponse>() {
                    @Override
                    public void success(UpdateDebtResponse res) {
                        getAllDebt();
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

    private void parseToJarDetailDTO(List<Debt> spending) {
        List<DebtDTO> incomeDTOs = new ArrayList<>();
        for (Debt item : spending) {
            incomeDTOs.add(new DebtDTO(item));
        }

        while ((incomeDTOs.size() - 1) >= 0) {
            List<IJarDetail> listChildDebtDTO = new ArrayList<>();
            listChildDebtDTO.add(incomeDTOs.get(0));

            DebtDTO startDebtDTO = incomeDTOs.get(0);
            incomeDTOs.remove(0);

            for (int i = incomeDTOs.size() - 1; i >=0; i--) {
                if (DateUtils.compareDate(startDebtDTO.getDate(), incomeDTOs.get(i).getDate()) == 0) {
                    listChildDebtDTO.add(incomeDTOs.get(i));
                    incomeDTOs.remove(i);
                }
            }

            mList.add(new JarDetailDTO(startDebtDTO.getDate(), listChildDebtDTO));
        }
    }
}
