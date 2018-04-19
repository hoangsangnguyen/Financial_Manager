package com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.nhattruong.financialmanager.mvp.jar_detail.fragments.IJarDetail;

import java.util.List;

public class JarDetailExpandableAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<IJarDetail> mItems;

    public JarDetailExpandableAdapter(Context mContext, List<IJarDetail> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
