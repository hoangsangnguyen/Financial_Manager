package com.example.nhattruong.financialmanager.model;


public class Detail {

    private String title;
    private float moneyThu;
    private float moneyChi;

    public Detail(String title, float moneyThu, float moneyChi) {
        this.title = title;
        this.moneyThu = moneyThu;
        this.moneyChi = moneyChi;
    }

    public String getTitle() {
        return title;
    }

    public float getMoneyThu() {
        return moneyThu;
    }

    public float getMoneyChi() {
        return moneyChi;
    }
}
