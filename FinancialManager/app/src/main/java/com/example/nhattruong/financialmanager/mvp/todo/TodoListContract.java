package com.example.nhattruong.financialmanager.mvp.todo;

import com.example.nhattruong.financialmanager.base.IBaseView;

public interface TodoListContract {
    interface View extends IBaseView {
        void deleteSuccess();
        void deleteFailed();
    }

    interface Presenter {
        void remove(int position);
    }
}
