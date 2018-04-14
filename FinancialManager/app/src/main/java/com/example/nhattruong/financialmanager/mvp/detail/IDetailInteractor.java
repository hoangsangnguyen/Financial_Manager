package com.example.nhattruong.financialmanager.mvp.detail;

import com.example.nhattruong.financialmanager.model.Debt;
import com.example.nhattruong.financialmanager.model.Income;
import com.example.nhattruong.financialmanager.model.Spending;

import java.util.List;

public interface IDetailInteractor {

    interface IViewSpendings {
        void showSuccess(List<Spending> spendingList);

        void showFailed();
    }

    interface IViewSpendingsInteractor {
        void sendSuccess(List<Spending> spendingList);

        void sendFailure();
    }

    interface IViewIncomes {
        void showSuccess(List<Income> incomeList);

        void showFailed();
    }

    interface IViewIncomesInteractor {
        void sendSuccess(List<Income> incomeList);

        void sendFailure();
    }

    interface IViewDebts {
        void showSuccess(List<Debt> debtList);

        void showFailed();
    }

    interface IViewDebtsInteractor {
        void sendSuccess(List<Debt> debtList);

        void sendFailure();
    }
}
