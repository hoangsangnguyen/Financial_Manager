package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.DebtUpdateRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.DebtResponse;
import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.debt.dto.DebtDTO;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.dto.JarDetailDTO;

import java.util.ArrayList;
import java.util.List;

public class DebtPresenter extends BasePresenter implements DebtContract.Presenter {

    private List<JarDetailDTO> mList;
    private String mJarId;
    private int mPositionGroup, mPositionChild;

    public void setJarId(String jarId) {
        this.mJarId = jarId;
    }

    public void setPositionGroupAndChild(int positionGroup, int positionChild){
        this.mPositionGroup = positionGroup;
        this.mPositionChild = positionChild;
    }

    public List<JarDetailDTO> getListDebt() {
        if (mList == null){
            mList = new ArrayList<>();
        }
        return mList;
    }

    @Override
    public DebtContract.View getView() {
        return (DebtContract.View)super.getView();
    }

    @Override
    public void getAllDebt() {
        if (!isViewAttached()) return;
        getView().showLoading();

        getApiManager().getAllDebt(getPreferManager().getUser().getId(), mJarId, new ApiCallback<DebtResponse>() {
            @Override
            public void success(DebtResponse res) {
                getListDebt().clear();
                parseToJarDetailDTO(res.getDebts());
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().getAllDebtSuccess();
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
                getPreferManager().getUser().getId(),
                mJarId,
                getListDebt().get(positionGroup).getList().get(positionChild).getId(),
                new ApiCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        getListDebt().get(positionGroup).getList().remove(positionChild);
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getView().deleteDebtSuccess();
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
                getPreferManager().getUser().getId(),
                mJarId,
                debt.getId(),
                request,
                new ApiCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        setDebtChanged(debt);
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getView().updateDebtSuccess();
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

    private void setDebtChanged(Debt debt) {
        DebtDTO debtDTO = new DebtDTO();
        debtDTO.setDebtChanged(debt);
        getListDebt().get(mPositionGroup).getList().set(mPositionChild, debtDTO);
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
                if (compareDate(startDebtDTO.getDate(), incomeDTOs.get(i).getDate())) {
                    listChildDebtDTO.add(incomeDTOs.get(i));
                    incomeDTOs.remove(i);
                }
            }

            mList.add(new JarDetailDTO(startDebtDTO.getDate(), listChildDebtDTO));
        }
    }

    private boolean compareDate(String date1, String date2){
        date1 = date1.substring(0, date1.indexOf("T"));
        date2 = date2.substring(0, date2.indexOf("T"));

        return date1.equalsIgnoreCase(date2);
    }
}
