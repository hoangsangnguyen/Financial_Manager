package com.example.nhattruong.financialmanager.mvp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.dialog.DialogPositiveNegative;
import com.example.nhattruong.financialmanager.dialog.dialogAddIncome.DialogAddIncome;
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.mvp.home.adapter.JarAdapter;
import com.example.nhattruong.financialmanager.mvp.jar_detail.JarDetailActivity;
import com.example.nhattruong.financialmanager.mvp.login.LoginActivity;
import com.example.nhattruong.financialmanager.mvp.income.CreateIncomeActivity;
import com.example.nhattruong.financialmanager.mvp.profile.ProfileActivity;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeContract.View, View.OnClickListener {

    public static final String ADD_INCOME_FOR_JAR = "ADD_INCOME_FOR_JAR";
    public static final int REQUEST_CODE_CREATE = 22;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.tb_home)
    Toolbar toolbar;

    @BindView(R.id.iv_add)
    ImageView ivAdd;

    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;

    @BindView(R.id.tv_logout)
    TextView tvLogout;

    @BindView(R.id.refresh_jar)
    SwipeRefreshLayout mRefresh;

    @BindView(R.id.rcv_jar)
    RecyclerView rcvJar;

 /*   @BindView(R.id.bmb)
    BoomMenuButton bmb;*/

    @BindView(R.id.fab_add)
    FloatingActionMenu fabAdd;

    @BindView(R.id.fab_income)
    FloatingActionButton fabIncome;

    @BindView(R.id.fab_spending)
    FloatingActionButton fabSpending;

    @BindView(R.id.fab_debt)
    FloatingActionButton fabDebt;

    @BindView(R.id.fab_general)
    FloatingActionButton fabGeneral;

    JarAdapter mJarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new HomePresenter());
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    public HomePresenter getPresenter() {
        return (HomePresenter) super.getPresenter();
    }

    @Override
    public void onInitData() {

        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        User user = getPresenter().getPreferManager().getUser();
        tvUserName.setText(user.getUserName());
        tvUserEmail.setText(user.getEmail());

        rcvJar.setLayoutManager(new GridLayoutManager(this, 2));
        mJarAdapter = new JarAdapter(this, getPresenter().getJarList(), new JarAdapter.Listener() {
            @Override
            public void onItemClicked(int position) {
                navigationView.getMenu().getItem(position).setChecked(true);
                Intent intent = new Intent(HomeActivity.this, JarDetailActivity.class);
                intent.putExtra(AppConstants.JAR_ID, getPresenter().getJarList().get(position).getId());
                startActivity(intent);
            }
        });
        rcvJar.setAdapter(mJarAdapter);
        getPresenter().getJars();
    }

    @Override
    public void onInitListener() {
        tvUserName.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        fabIncome.setOnClickListener(this);
        fabSpending.setOnClickListener(this);
        fabDebt.setOnClickListener(this);
        fabGeneral.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                String jarId = "";

                switch (item.getItemId()) {
                    case R.id.jar1:
                        jarId = getPresenter().getJarList().get(0).getId();
                        break;
                    case R.id.jar2:
                        jarId = getPresenter().getJarList().get(1).getId();
                        break;
                    case R.id.jar3:
                        jarId = getPresenter().getJarList().get(2).getId();
                        break;
                    case R.id.jar4:
                        jarId = getPresenter().getJarList().get(3).getId();
                        break;
                    case R.id.jar5:
                        jarId = getPresenter().getJarList().get(4).getId();
                        break;
                    case R.id.jar6:
                        jarId = getPresenter().getJarList().get(5).getId();
                        break;
                }

                Intent intentDetail = new Intent(HomeActivity.this, JarDetailActivity.class);
                intentDetail.putExtra(AppConstants.JAR_ID, jarId);
                startActivity(intentDetail);
                return true;
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog("Are you sure to logout?", new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        dialog.dismiss();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        intent.putExtra(LoginActivity.USER_NAME, getPresenter().getPreferManager().getUser().getUserName());
                        getPresenter().getPreferManager().resetUser();
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getJars();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onLoadJarsSuccess() {
        mRefresh.setRefreshing(false);

      /*  bmb.clearBuilders();

        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_6);

        for (int i=0; i<getPresenter().getJarList().size(); i++){
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalText(getPresenter().getJarList().get(i).getType())
                    .normalImageRes(R.drawable.ic_small_jar)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            navigationView.getMenu().getItem(index).setChecked(true);
                            Intent intentDetail = new Intent(HomeActivity.this, JarDetailActivity.class);
                            intentDetail.putExtra(AppConstants.JAR_ID, getPresenter().getJarList().get(index).getId());
                            Log.d("JAR HOME", String.valueOf(index));
                            startActivity(intentDetail);
                        }
                    });
            bmb.addBuilder(builder);
        }*/

        mJarAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadJarsFailed(RestError error) {
        mRefresh.setRefreshing(false);
        showErrorDialog(error.message);
    }

    @Override
    public void onClick(View view) {
        if (view == tvUserName) {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        } else if (view == ivAdd) {

            DialogAddIncome dialogAddIncome = new DialogAddIncome(this, new DialogAddIncome.onClickItemListener() {
                @Override
                public void onAddIncomeForJar() {
                    Intent intentAddIncome = new Intent(HomeActivity.this, CreateIncomeActivity.class);
                    intentAddIncome.putExtra(ADD_INCOME_FOR_JAR, true);
                    startActivityForResult(intentAddIncome, REQUEST_CODE_CREATE);
                }

                @Override
                public void onAddGeneralIncome() {
                    Intent intentAddIncome = new Intent(HomeActivity.this, CreateIncomeActivity.class);
                    intentAddIncome.putExtra(ADD_INCOME_FOR_JAR, false);
                    startActivityForResult(intentAddIncome, REQUEST_CODE_CREATE);
                }
            });
            dialogAddIncome.show();
        } else {
            Intent intentCreate = new Intent(this, CreateIncomeActivity.class);
            if (view == fabIncome){
                intentCreate.putExtra(CreateIncomeActivity.CREATE_TYPE, AppConstants.CREATE_INCOME);
            } else if (view == fabSpending){
                intentCreate.putExtra(CreateIncomeActivity.CREATE_TYPE, AppConstants.CREATE_SPENDING);
            } else if (view == fabDebt){
                intentCreate.putExtra(CreateIncomeActivity.CREATE_TYPE, AppConstants.CREATE_DEBT);
            } else if (view == fabGeneral)
            {
                intentCreate.putExtra(CreateIncomeActivity.CREATE_TYPE, AppConstants.CREATE_GENERAL);
            }
            startActivityForResult(intentCreate, REQUEST_CODE_CREATE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CREATE) {
            getPresenter().getJars();
        }
    }
}
