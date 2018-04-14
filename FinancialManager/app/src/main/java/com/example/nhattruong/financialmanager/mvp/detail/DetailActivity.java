package com.example.nhattruong.financialmanager.mvp.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.mvp.detail.debts.DebtsFragment;
import com.example.nhattruong.financialmanager.mvp.detail.incomes.IncomesFragment;
import com.example.nhattruong.financialmanager.mvp.detail.spendings.SpendingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_detail)
    TextView tvTitleDetail;
    @BindView(R.id.rl_fragment_detail)
    RelativeLayout rlFragmentDetail;
    @BindView(R.id.tv_spendings_detail)
    TextView tvSpendingsDetail;
    @BindView(R.id.tv_incomes_detail)
    TextView tvIncomesDetail;
    @BindView(R.id.tv_debts_detail)
    TextView tvDebtsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        tvSpendingsDetail.setOnClickListener(this);
        tvIncomesDetail.setOnClickListener(this);
        tvDebtsDetail.setOnClickListener(this);
        replaceFragmentContent(SpendingsFragment.newInstance());

    }

    @Override
    public void onClick(View view) {
        if (view == tvSpendingsDetail) {
            replaceFragmentContent(SpendingsFragment.newInstance());
            setTextColor(1);
        } else if (view == tvIncomesDetail) {
            replaceFragmentContent(IncomesFragment.newInstance());
            setTextColor(2);
        } else if (view == tvDebtsDetail) {
            replaceFragmentContent(DebtsFragment.newInstance());
            setTextColor(3);
        }
    }

    private void replaceFragmentContent(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment_detail, fragment);
            fragmentTransaction.commit();
        }
    }

    private void setTextColor(int color) {
        switch (color) {
            case 1:
                tvTitleDetail.setText("SPENDINGS");
                tvSpendingsDetail.setBackgroundColor(Color.parseColor("#f5dd03"));
                tvIncomesDetail.setBackgroundColor(Color.parseColor("#ffffff"));
                tvDebtsDetail.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case 2:
                tvTitleDetail.setText("INCOMES");
                tvSpendingsDetail.setBackgroundColor(Color.parseColor("#ffffff"));
                tvIncomesDetail.setBackgroundColor(Color.parseColor("#f5dd03"));
                tvDebtsDetail.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case 3:
                tvTitleDetail.setText("DEBTS");
                tvSpendingsDetail.setBackgroundColor(Color.parseColor("#ffffff"));
                tvIncomesDetail.setBackgroundColor(Color.parseColor("#ffffff"));
                tvDebtsDetail.setBackgroundColor(Color.parseColor("#f5dd03"));
                break;
            default:
                tvTitleDetail.setText("SPENDINGS");
                tvSpendingsDetail.setBackgroundColor(Color.parseColor("#f5dd03"));
                tvIncomesDetail.setBackgroundColor(Color.parseColor("#ffffff"));
                tvDebtsDetail.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }
}
