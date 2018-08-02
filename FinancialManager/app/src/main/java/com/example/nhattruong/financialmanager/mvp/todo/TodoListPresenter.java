package com.example.nhattruong.financialmanager.mvp.todo;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.model.Todo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

public class TodoListPresenter extends BasePresenter implements TodoListContract.Presenter {

    private List<Todo> todoList;

    public List<Todo> getTodoList() {
        if (todoList == null){
            todoList = new ArrayList<>();
        }
        return todoList;
    }

    @Override
    public void onCreate(IBaseView view, Unbinder binder) {
        super.onCreate(view, binder);
        todoList = getSQLiteManager().getTodoList();
    }

    @Override
    public TodoListContract.View getView() {
        return (TodoListContract.View)super.getView();
    }

    @Override
    public void remove(int position) {
        if (!getTodoList().isEmpty()){
            todoList.remove(position);
            getSQLiteManager().saveTodoList(todoList);
            if (!isViewAttached()) return;
            getView().deleteSuccess();
        } else {
            if (!isViewAttached()) return;
            getView().deleteFailed();
        }
    }
}
