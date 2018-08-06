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
import com.example.nhattruong.financialmanager.interactor.api.network.RestError;
import com.example.nhattruong.financialmanager.interactor.api.response.StatisticResponse;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.mvp.chart.ChartActivity;
import com.example.nhattruong.financialmanager.mvp.create.CreateActivity;
import com.example.nhattruong.financialmanager.mvp.home.adapter.JarAdapter;
import com.example.nhattruong.financialmanager.mvp.jar_detail.JarDetailActivity;
import com.example.nhattruong.financialmanager.mvp.login.LoginActivity;
import com.example.nhattruong.financialmanager.mvp.profile.ProfileActivity;
import com.example.nhattruong.financialmanager.mvp.todo.TodoListActivity;
import com.example.nhattruong.financialmanager.reminder.ReminderService;
import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeContract.View, View.OnClickListener {

    public static final String STATISTIC = "STATISTIC";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.tb_home)
    Toolbar toolbar;

    @BindView(R.id.iv_chart)
    ImageView ivChart;

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

    @BindView(R.id.fab_add)
    FloatingActionMenu fabAdd;

    @BindView(R.id.fab_income)
    FloatingActionButton fabIncome;

    @BindView(R.id.fab_spending)
    FloatingActionButton fabSpending;

    @BindView(R.id.fab_debt)
    FloatingActionButton fabDebt;

    private JarAdapter mJarAdapter;
    private boolean isCreateTodo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new HomePresenter());
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);

        Intent reminderIntent = new Intent(ReminderService.ACTION_INIT_EVENT_REMINDER);

        ReminderService.enqueueWork(this, reminderIntent);
    }

    @Override
    public HomePresenter getPresenter() {
        return (HomePresenter) super.getPresenter();
    }

    @Override
    public void onInitData() {

        FirebaseMessaging.getInstance().subscribeToTopic("Notification");

        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        User user = getPresenter().getSQLiteManager().getUser();
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
        ivChart.setOnClickListener(this);
        fabIncome.setOnClickListener(this);
        fabSpending.setOnClickListener(this);
        fabDebt.setOnClickListener(this);

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
                    case R.id.todo:
                        isCreateTodo = true;
                        Intent intentCreateTodo = new Intent(HomeActivity.this, TodoListActivity.class);
                        startActivityForResult(intentCreateTodo, AppConstants.REQUEST_CODE_CREATE_TODO);
                        break;
                }

                if (!isCreateTodo){
                    Intent intentDetail = new Intent(HomeActivity.this, JarDetailActivity.class);
                    intentDetail.putExtra(AppConstants.JAR_ID, jarId);
                    startActivity(intentDetail);
                }

                isCreateTodo = false;

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
                        intent.putExtra(LoginActivity.USER_NAME, getPresenter().getSQLiteManager().getUser().getUserName());
                        getPresenter().getSQLiteManager().resetUser();
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
        mJarAdapter.notifyDataSetChanged();
    }

    @Override
    public void getStatisticSuccess(StatisticResponse response) {
        Intent intentChart = new Intent(this, ChartActivity.class);
        intentChart.putExtra(STATISTIC, response);
        startActivity(intentChart);
    }

    @Override
    public void onFailure(RestError error) {
        mRefresh.setRefreshing(false);
        showErrorDialog(error.message);
    }

    @Override
    public void onClick(View view) {
        if (view == tvUserName) {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        }else if (view == ivChart){
            getPresenter().getStatistic();
        }else {
            Intent intentCreate = new Intent(this, CreateActivity.class);
            if (view == fabIncome){
                intentCreate.putExtra(CreateActivity.CREATE_TYPE, AppConstants.CREATE_INCOME);
            } else if (view == fabSpending){
                intentCreate.putExtra(CreateActivity.CREATE_TYPE, AppConstants.CREATE_SPENDING);
            } else if (view == fabDebt){
                intentCreate.putExtra(CreateActivity.CREATE_TYPE, AppConstants.CREATE_DEBT);
            }
            startActivityForResult(intentCreate, AppConstants.REQUEST_CODE_CREATE);
            fabAdd.close(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.REQUEST_CODE_CREATE){
                getPresenter().getJars();
            }
            else if (requestCode == AppConstants.REQUEST_CODE_CREATE_TODO){
               /* Todo todo = (Todo) data.getSerializableExtra(AppConstants.TODO);
                new ReminderService().sendNotification(todo);*/
            }
        }
    }
}
