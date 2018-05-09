package com.example.nhattruong.financialmanager.interactor.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatisticResponse extends BaseResponse implements Serializable{

    @SerializedName("results")
    @Expose
    private Statistic result;

    public Statistic getResult() {
        return result;
    }

    public class Statistic implements Serializable{

        @SerializedName("incomes")
        @Expose
        private List<Double> incomes;

        @SerializedName("spendings")
        @Expose
        private List<Double> spendings;

        @SerializedName("debts")
        @Expose
        private List<Double> debts;

        public List<Double> getIncomes() {
            if (incomes == null){
                incomes = new ArrayList<>();
            }
            return incomes;
        }

        public List<Double> getSpendings() {
            if (spendings == null){
                spendings = new ArrayList<>();
            }
            return spendings;
        }

        public List<Double> getDebts() {
            if (debts == null){
                debts = new ArrayList<>();
            }
            return debts;
        }
    }
}
