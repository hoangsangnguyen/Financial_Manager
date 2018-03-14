package com.example.nhattruong.financialmanager.interactor.resources;

import android.content.Context;

import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;

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

   /* public List<String> getListDialogBottomUser() {
        List<String> results = new ArrayList<>();
        results.add(mContext.getString(R.string.send_message));
        return results;
    }

    public List<String> getListDialogBottomUserAdmin() {
        List<String> results = new ArrayList<>();
        results.add(mContext.getString(R.string.send_message));
        results.add(mContext.getString(R.string.remove_from_group));
        return results;
    }

    public List<String> getListOptionMyMessage() {
        List<String> listOptionMM = new ArrayList<>();
        listOptionMM.add(mContext.getString(R.string.forward));
        listOptionMM.add(mContext.getString(R.string.remove));
        listOptionMM.add(mContext.getString(R.string.edit));
        listOptionMM.add(mContext.getString(R.string.quote));
        listOptionMM.add(mContext.getString(R.string.copy));
        return listOptionMM;
    }

    public List<String> getListOptionOtherMessage() {
        List<String> listOptionOM = new ArrayList<>();
        listOptionOM.add(mContext.getString(R.string.forward));
        listOptionOM.add(mContext.getString(R.string.quote));
        listOptionOM.add(mContext.getString(R.string.copy));
        return listOptionOM;
    }*/
}
