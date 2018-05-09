package com.example.nhattruong.financialmanager.mvp.chart.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseFragment;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChartFragment extends BaseFragment {

    @BindView(R.id.line_chart)
    LineChart lineChart;

    public static ChartFragment newInstance(String jarId){
        Bundle args = new Bundle();
        args.putString(AppConstants.JAR_ID, jarId);
        ChartFragment fragment = new ChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new ChartFragmentPresenter());
    }

    @Override
    public ChartFragmentPresenter getPresenter() {
        return (ChartFragmentPresenter) super.getPresenter();
    }

    @Override
    protected void onInitData() {

        getPresenter().setJarId(getArguments().getString(AppConstants.JAR_ID));

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        List<Entry> yValues1 = new ArrayList<>();
        yValues1.add(new Entry(0,60f));
        yValues1.add(new Entry(1,50f));
        yValues1.add(new Entry(2,70f));
        yValues1.add(new Entry(3,40f));
        yValues1.add(new Entry(4,80f));
        yValues1.add(new Entry(5,90f));
        yValues1.add(new Entry(6,30f));
        yValues1.add(new Entry(7,60f));

        List<Entry> yValues2 = new ArrayList<>();
        yValues2.add(new Entry(0,30f));
        yValues2.add(new Entry(1,50f));
        yValues2.add(new Entry(2,90f));
        yValues2.add(new Entry(3,40f));
        yValues2.add(new Entry(4,20f));
        yValues1.add(new Entry(5,80f));
        yValues2.add(new Entry(6,20f));
        yValues2.add(new Entry(7,60f));

        LineDataSet set1 = new LineDataSet(yValues1, "Data set 1");
        set1.setFillAlpha(110);
        set1.setColor(ContextCompat.getColor(getActivity(), R.color.red));

        LineDataSet set2 = new LineDataSet(yValues2, "Data set 2");
        set2.setFillAlpha(100);
        set2.setColor(ContextCompat.getColor(getActivity(), R.color.blue_color));

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
    }

    @Override
    protected void onInitListener() {

    }


}
