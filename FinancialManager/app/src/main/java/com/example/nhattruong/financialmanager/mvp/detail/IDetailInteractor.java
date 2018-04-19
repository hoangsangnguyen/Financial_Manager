package com.example.nhattruong.financialmanager.mvp.detail;

import com.example.nhattruong.financialmanager.model.DateDebts;
import com.example.nhattruong.financialmanager.model.DateIncomes;
import com.example.nhattruong.financialmanager.model.DateSpendings;

import java.util.Date;
import java.util.List;

public interface IDetailInteractor {

    interface IViewSpendings {
        void getIdSuccess(String token, String userId, String jarId);

        void getIdFailure();

        void showSuccess(List<DateSpendings> dateDebtsList);

        void showFailed();
    }

    interface IViewSpendingsInteractor {
        void sendIdSuccess(String token, String useId, String jarId);

        void sendIdFailure();

        void sendSuccess(List<DateSpendings> dateDebtsList);

        void sendFailure();
    }

    interface IViewIncomes {
        void getIdSuccess(String token, String userId, String jarId);

        void getIdFailure();

        void showSuccess(List<DateIncomes> dateDebtsList);

        void showFailed();
    }

    interface IViewIncomesInteractor {
        void sendIdSuccess(String token, String useId, String jarId);

        void sendIdFailure();

        void sendSuccess(List<DateIncomes> dateDebtsList);

        void sendFailure();
    }

    interface IViewDebts {
        void getIdSuccess(String token, String userId, String jarId);

        void getIdFailure();

        void showSuccess(List<DateDebts> dateDebtsList);

        void showFailed();
    }

    interface IViewDebtsInteractor {
        void sendIdSuccess(String token, String useId, String jarId);

        void sendIdFailure();

        void sendSuccess(List<DateDebts> dateDebtsList);

        void sendFailure();
    }

    interface IChangeListData<K, V> {
        boolean checkDate(List<K> dateList, String date);

        List<K> changeListData(List<K> dateList, List<V> list);
    }
}
