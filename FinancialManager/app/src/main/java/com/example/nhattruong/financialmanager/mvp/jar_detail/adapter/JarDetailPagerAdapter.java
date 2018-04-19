package com.example.nhattruong.financialmanager.mvp.jar_detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.BaseJarDetailFragment;

import java.util.List;

public class JarDetailPagerAdapter extends FragmentPagerAdapter {

    private List<BaseJarDetailFragment> mListFragments;

    public JarDetailPagerAdapter(FragmentManager fm, List<BaseJarDetailFragment> mListFragments) {
        super(fm);
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
}
