package com.example.nhattruong.financialmanager.mvp.todo;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.interactor.api.network.ApiCallback;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.request.CreateTodoRequest;
import com.example.nhattruong.financialmanager.interactor.api.response.CreateTodoResponse;
import com.example.nhattruong.financialmanager.model.Todo;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Unbinder;

public class TodoPresenter extends BasePresenter implements TodoContract.Presenter {

    private Date mDate;
    private List<Todo> todoList;

    public Date getDate() {
        if (mDate == null){
            mDate = DateUtils.getCurrentDate();
        }
        return mDate;
    }

    public List<Todo> getTodoList() {
        if (todoList == null){
            todoList = new ArrayList<>();
        }
        return todoList;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    @Override
    public void onCreate(IBaseView view, Unbinder binder) {
        super.onCreate(view, binder);
        todoList = getSQLiteManager().getTodoList();
    }

    @Override
    public TodoContract.View getView() {
        return (TodoContract.View)super.getView();
    }

    @Override
    public void creteTodo(String content) {
        if (!isViewAttached()) return;
        getView().showLoading();
        todoList.add(new Todo(content, getDate()));
        getSQLiteManager().saveTodoList(todoList);
        if (!isViewAttached()) return;
        getView().hideLoading();
        if (!isViewAttached()) return;
        getView().onSuccess();

        /*CreateTodoRequest request = new CreateTodoRequest(content, DateUtils.formatDateFilter(getDate()));
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
        });*/
    }


}
