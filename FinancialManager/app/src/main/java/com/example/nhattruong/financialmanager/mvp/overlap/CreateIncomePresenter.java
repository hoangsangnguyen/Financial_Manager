package com.example.nhattruong.financialmanager.mvp.overlap;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.JarResponse;
import com.example.nhattruong.financialmanager.model.Jar;

import java.util.ArrayList;
import java.util.List;

public class CreateIncomePresenter extends BasePresenter implements CreateIncomeContract.Presenter{
    
    private List<Jar> mJarList;
    private String mCurrentJarId;

    public List<Jar> getJarList() {
        if (mJarList == null){
            mJarList = new ArrayList<>();
        }
        return mJarList;
    }

    public String getCurrentJarId() {
        return mCurrentJarId;
    }

    public void setCurrentJarId(String currentJarId) {
        this.mCurrentJarId = currentJarId;
    }

    @Override
    public CreateIncomeContract.View getView() {
        return (CreateIncomeContract.View)super.getView();
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
                if (res.result != null && !res.result.isEmpty()){
                    mJarList = res.result;
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
}
