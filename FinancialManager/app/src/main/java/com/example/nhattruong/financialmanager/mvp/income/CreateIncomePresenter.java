package com.example.nhattruong.financialmanager.mvp.income;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateIncomeJarRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.BaseResponse;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.model.Jar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateIncomePresenter extends BasePresenter implements CreateIncomeContract.Presenter {

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
    public CreateIncomeContract.View getView() {
        return (CreateIncomeContract.View) super.getView();
    }

    @Override
    public void getAllJar() {
        if (!isViewAttached()) return;
        getView().showLoading();
        getApiManager().getJars(getPreferManager().getUser().getId(), new ApiCallback<JarResponse>() {
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

        CreateIncomeJarRequest request =
                new CreateIncomeJarRequest.Builder().setDate(date).setDetail(detail).setAmount(amount).build();
        getApiManager().createIncomeForJar(getPreferManager().getUser().getId(), mJarId, request, new ApiCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse res) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().createIncomeJarSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().createIncomeJarFailed(error);
            }
        });
    }

    @Override
    public void createGeneralIncome(Date date, String detail, double amount) {
        if (!isViewAttached()) return;
        getView().showLoading();

        CreateIncomeJarRequest request =
                new CreateIncomeJarRequest.Builder().setDate(date).setDetail(detail).setAmount(amount).build();
        getApiManager().createGeneralIncome(getPreferManager().getUser().getId(), request, new ApiCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse res) {
                if (!isViewAttached()) return;
                getView().hideLoading();
                getView().createIncomeJarSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().createIncomeJarFailed(error);
            }
        });
    }
}
