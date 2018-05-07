package com.example.nhattruong.financialmanager.mvp.create;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateDebtRequest;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateIncomeSpendingRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.model.Jar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreatePresenter extends BasePresenter implements CreateContract.Presenter {

    private List<Jar> mJarList;
    private String mJarId;

    @Override
    public void setJarId(String jarId) {
        this.mJarId = jarId;
    }

    public List<Jar> getJarList() {
        if (mJarList == null) {
            mJarList = new ArrayList<>();
        }
        return mJarList;
    }

    @Override
    public CreateContract.View getView() {
        return (CreateContract.View) super.getView();
    }

    @Override
    public void getAllJar() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().getJars(getSQLiteManager().getUser().getId(), new ApiCallback<JarResponse>() {
            @Override
            public void success(JarResponse res) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                if (res.result != null && !res.result.isEmpty()) {
                    getJarList().clear();
                    mJarList.addAll(res.result);
                }
                getView().getAllJarSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
            }
        });
    }

    @Override
    public void createIncomeForJar(Date date, String detail, double amount) {
        if (!isViewAttached()) return;
        getView().showLoading();

        CreateIncomeSpendingRequest request =
                new CreateIncomeSpendingRequest.Builder().setDate(date).setDetail(detail).setAmount(amount).build();
        getApiManager().createIncomeForJar(getSQLiteManager().getUser().getId(), mJarId, request, new ApiCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse res) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().createSuccess();
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
    public void createGeneralIncome(Date date, String detail, double amount) {
        if (!isViewAttached()) return;
        getView().showLoading();

        CreateIncomeSpendingRequest request =
                new CreateIncomeSpendingRequest.Builder().setDate(date).setDetail(detail).setAmount(amount).build();
        getApiManager().createGeneralIncome(getSQLiteManager().getUser().getId(), request, new ApiCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse res) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().createSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().onFailure(error);
            }
        });
    }

    @Override
    public void createSpending(Date date, String detail, double amount) {
        if (!isViewAttached())return;
        getView().showLoading();

        CreateIncomeSpendingRequest request =
                new CreateIncomeSpendingRequest.Builder().setDate(date).setDetail(detail).setAmount(amount).build();
        getApiManager().createSpending(
                getSQLiteManager().getUser().getId(),
                mJarId,
                request,
                new ApiCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getView().createSuccess();
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
    public void createDebt(Date date, String detail, double amount, String origin, String state, boolean isPositive) {

        CreateDebtRequest request = new CreateDebtRequest.Builder()
                .setDate(date).setDetail(detail).setAmount(amount).setOrigin(origin).setState(state).setPositive(isPositive).build();

        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().createDebt(
                getSQLiteManager().getUser().getId(),
                mJarId,
                request,
                new ApiCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        if (!isViewAttached()) return;
                        getView().hideLoading();
                        getView().createSuccess();
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
}
