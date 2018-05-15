package com.example.nhattruong.financialmanager.mvp.chart;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.StatisticResponse;
import com.example.nhattruong.financialmanager.mvp.home.HomeActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChartActivity extends BaseActivity implements ChartContract.View {

    @BindView(R.id.ln_left)
    LinearLayout lnLeft;

    @BindView(R.id.refresh_chart)
    SwipeRefreshLayout refresh;

    @BindView(R.id.line_chart)
    LineChart lineChart;

    private List<ILineDataSet> dataSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new ChartPresenter());
        setContentView(R.layout.activity_chart);
        super.onCreate(savedInstanceState);
    }

    @Override
    public ChartPresenter getPresenter() {
        return (ChartPresenter)super.getPresenter();
    }

    @Override
    public void onInitData() {

        dataSets = new ArrayList<>();

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        if (getIntent().getSerializableExtra(HomeActivity.STATISTIC)!= null){
            StatisticResponse response = (StatisticResponse) getIntent().getSerializableExtra(HomeActivity.STATISTIC);


            List<Entry> yValueIncomes = new ArrayList<>();
            List<Double> incomes = response.getResult().getIncomes();
            for (int i=0;i<incomes.size() - 1; i++){
                yValueIncomes.add(new Entry(i,(float) incomes.get(i).doubleValue()));
            }
            LineDataSet dsIncomes = new LineDataSet(yValueIncomes, getString(R.string.incomes));
            dsIncomes.setFillAlpha(110);
            dsIncomes.setColor(ContextCompat.getColor(this, R.color.app_color));

            List<Entry> yValueSpendings = new ArrayList<>();
            List<Double> spendings = response.getResult().getSpendings();
            for (int i=0;i<spendings.size() - 1; i++){
                yValueSpendings.add(new Entry(i,(float) spendings.get(i).doubleValue()));
            }
            LineDataSet dsSpendings = new LineDataSet(yValueSpendings, getString(R.string.spending));
            dsSpendings.setFillAlpha(110);
            dsSpendings.setColor(ContextCompat.getColor(this, R.color.yellow_color));

            List<Entry> yValueDebts = new ArrayList<>();
            List<Double> debts = response.getResult().getDebts();
            for (int i=0;i<debts.size() - 1; i++){
                yValueDebts.add(new Entry(i,(float) debts.get(i).doubleValue()));
            }
            LineDataSet dsDebts= new LineDataSet(yValueDebts, getString(R.string.debts));
            dsDebts.setFillAlpha(110);
            dsDebts.setColor(ContextCompat.getColor(this, R.color.red));

            dataSets.add(dsIncomes);
            dataSets.add(dsSpendings);
            dataSets.add(dsDebts);

            LineData data = new LineData(dataSets);
            lineChart.setData(data);
            lineChart.invalidate();
        }
    }

    @Override
    public void onInitListener() {
        lnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getStatistic();
            }
        });
    }

    @Override
    public void onSuccess(StatisticResponse response) {

        refresh.setRefreshing(false);

        if (dataSets != null && !dataSets.isEmpty()) dataSets.clear();

        if (response.getResult()==null) return;

        List<Entry> yValueIncomes = new ArrayList<>();
        List<Double> incomes = response.getResult().getIncomes();
        for (int i=0;i<incomes.size() - 1; i++){
            yValueIncomes.add(new Entry(i,(float) incomes.get(i).doubleValue()));
        }
        LineDataSet dsIncomes = new LineDataSet(yValueIncomes, getString(R.string.incomes));
        dsIncomes.setFillAlpha(110);
        dsIncomes.setColor(ContextCompat.getColor(this, R.color.app_color));

        List<Entry> yValueSpendings = new ArrayList<>();
        List<Double> spendings = response.getResult().getSpendings();
        for (int i=0;i<spendings.size() - 1; i++){
            yValueSpendings.add(new Entry(i,(float) spendings.get(i).doubleValue()));
        }
        LineDataSet dsSpendings = new LineDataSet(yValueSpendings, getString(R.string.spending));
        dsSpendings.setFillAlpha(110);
        dsSpendings.setColor(ContextCompat.getColor(this, R.color.yellow_color));

        List<Entry> yValueDebts = new ArrayList<>();
        List<Double> debts = response.getResult().getDebts();
        for (int i=0;i<debts.size() - 1; i++){
            yValueDebts.add(new Entry(i,(float) debts.get(i).doubleValue()));
        }
        LineDataSet dsDebts= new LineDataSet(yValueDebts, getString(R.string.debts));
        dsDebts.setFillAlpha(110);
        dsDebts.setColor(ContextCompat.getColor(this, R.color.red));

        dataSets.add(dsIncomes);
        dataSets.add(dsSpendings);
        dataSets.add(dsDebts);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
    }

    @Override
    public void onFailure(RestError restError) {
        refresh.setRefreshing(false);
        showErrorDialog(restError.message);
    }
}
