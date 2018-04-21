package com.example.nhattruong.financialmanager.mvp.jar_detail.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;

import java.util.List;

public class JarDetailPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    private List<BaseJarDetailFragment> mListFragments;

    public JarDetailPagerAdapter(Context context, FragmentManager fm, List<BaseJarDetailFragment> mListFragments) {
        super(fm);
        this.mContext = context;
        this.mListFragments = mListFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.incomes);
            case 1:
                return mContext.getString(R.string.spending);
            case 2:
                return mContext.getString(R.string.debts);
            default:
                return null;
        }
    }
}
