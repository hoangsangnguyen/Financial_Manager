package com.example.nhattruong.financialmanager.mvp.login.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.model.Detail;
import com.example.nhattruong.financialmanager.mvp.login.detail.adapter.ItemsDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.rcv_detail)
    RecyclerView rcvDetail;

    ItemsDetailAdapter itemsDetailAdapter;
    List<Detail> detailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        detailList = new ArrayList<>();

        Detail detail_1 = new Detail("Rau", 1000, 500);
        Detail detail_2 = new Detail("Gao", 1000, 500);
        Detail detail_3 = new Detail("Thit", 1000, 500);

        detailList.add(detail_1);
        detailList.add(detail_2);
        detailList.add(detail_3);

        itemsDetailAdapter = new ItemsDetailAdapter(this, detailList);
        rcvDetail.setLayoutManager(new LinearLayoutManager(this));
        rcvDetail.setAdapter(itemsDetailAdapter);
    }
}
