package com.example.nhattruong.financialmanager.mvp.chart.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.mvp.chart.fragments.ChartFragment;

import java.util.List;

public class ChartPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<ChartFragment> mItems;

    public ChartPagerAdapter(Context context,FragmentManager fm,  List<ChartFragment> items) {
        super(fm);
        this.mContext = context;
        this.mItems = items;
    }


    @Override
    public Fragment getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.incomes);
            case 1:
                return mContext.getString(R.string.spending);
            default:
                return null;
        }
    }
}
