package com.example.nhattruong.financialmanager.mvp.todo;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateTodoRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.CreateTodoResponse;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Date;

public class TodoPresenter extends BasePresenter implements TodoContract.Presenter {

    private Date mDate;

    public Date getDate() {
        if (mDate == null){
            mDate = DateUtils.getCurrentDate();
        }
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    @Override
    public TodoContract.View getView() {
        return (TodoContract.View)super.getView();
    }

    @Override
    public void creteTodo(String content) {
        if (!isViewAttached()) return;
        getView().showLoading();

        CreateTodoRequest request = new CreateTodoRequest(content, DateUtils.formatDateFilter(getDate()));
        getApiManager().createTodo(getSQLiteManager().getUser().getId(), request, new ApiCallback<CreateTodoResponse>() {
            @Override
            public void success(CreateTodoResponse res) {
                if (!isViewAttached()) return;
                getView().showLoading();
                getView().onSuccess(res.getTodo());
            }

            @Override
            public void failure(RestError error) {
                if (!isViewAttached()) return;
                getView().showLoading();
                getView().onFailure(error);
            }
        });
    }
}
