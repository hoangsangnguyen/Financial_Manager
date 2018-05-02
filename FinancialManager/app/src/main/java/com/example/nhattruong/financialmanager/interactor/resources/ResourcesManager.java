package com.example.nhattruong.financialmanager.interactor.resources;

import android.content.Context;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.model.TabHeader;

import java.util.ArrayList;
import java.util.List;

public class ResourcesManager {

    private Context mContext;
    private PreferManager mPreferManager;

    public ResourcesManager(Context context, PreferManager preferManager) {
        mContext = context;
        mPreferManager = preferManager;
    }

    public Context getContext() {
        return mContext;
    }

    public String getString(int resourceId) {
        return mContext.getString(resourceId);
    }

    public List<TabHeader> getListTabHeaderJarDetail() {
        List<TabHeader> tabHeaders = new ArrayList<>();
        tabHeaders.add(new TabHeader("  " + getString(R.string.incomes) + "  "));
        tabHeaders.add(new TabHeader("  " + getString(R.string.spending) + "  "));
        tabHeaders.add(new TabHeader("  " + getString(R.string.debts) + "  "));
        return tabHeaders;
    }
}
