package com.example.nhattruong.financialmanager.mvp.todo;

import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.Todo;

public interface TodoContract {
    interface View extends IBaseView{
        void onSuccess(Todo todo);

        void onFailure(RestError restError);
    }

    interface Presenter {
        void creteTodo(String content);
    }
}
