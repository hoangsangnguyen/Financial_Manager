package com.example.nhattruong.financialmanager.mvp.detail;

import com.example.nhattruong.financialmanager.model.DateDebts;
import com.example.nhattruong.financialmanager.model.DateIncomes;
import com.example.nhattruong.financialmanager.model.DateSpendings;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.model.Spending;

import java.util.List;

public interface IDetailInteractor {

    interface IViewSpendings {
        void showSuccess(List<DateSpendings> dateSpendingsList);

        void showFailed();
    }

    interface IViewSpendingsInteractor {
        void sendSuccess(List<DateSpendings> dateSpendingsList);

        void sendFailure();
    }

    interface IViewIncomes {
        void showSuccess(List<DateIncomes> dateIncomesList);

        void showFailed();
    }

    interface IViewIncomesInteractor {
        void sendSuccess(List<DateIncomes> dateIncomesList);

        void sendFailure();
    }

    interface IViewDebts {
        void showSuccess(List<DateDebts> dateDebtsList);

        void showFailed();
    }

    interface IViewDebtsInteractor {
        void sendSuccess(List<DateDebts> dateDebtsList);

        void sendFailure();
    }

    interface IChangeListData<K, V> {
        boolean checkDate(List<K> dateList, String date);

        List<K> changeListData(List<K> dateList, List<V> list);
    }
}
