package com.example.nhattruong.financialmanager.mvp.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.mvp.home.adapter.JarAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.tb_home)
    Toolbar toolbar;

    @BindView(R.id.rcv_jar)
    RecyclerView rcvJar;

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


        List<String> list = new ArrayList<>(Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff"));

        rcvJar.setLayoutManager(new GridLayoutManager(this, 2));
        rcvJar.setAdapter(new JarAdapter(this, list, new JarAdapter.Listener() {
            @Override
            public void onItemClicked(int position) {
                navigationView.getMenu().getItem(position).setChecked(true);
            }
        }));
    }

    @Override
    public void onInitListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
//                switch (item.getItemId()){
//                    case R.id.jar1:
//                        break;
//                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
