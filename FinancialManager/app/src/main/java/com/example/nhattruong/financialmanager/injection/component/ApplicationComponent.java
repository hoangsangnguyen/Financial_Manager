package com.example.nhattruong.financialmanager.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.nhattruong.financialmanager.MainApplication;
import com.example.nhattruong.financialmanager.base.BaseActivity;
import com.example.nhattruong.financialmanager.base.BaseFragment;
import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.injection.ApplicationContext;
import com.example.nhattruong.financialmanager.injection.module.ApiModule;
import com.example.nhattruong.financialmanager.injection.module.ApplicationModule;
import com.example.nhattruong.financialmanager.injection.module.NetworkModule;
import com.example.nhattruong.financialmanager.injection.module.PreferModule;
import com.example.nhattruong.financialmanager.injection.module.ResourcesModule;
import com.example.nhattruong.financialmanager.injection.module.SQLiteModule;
import com.example.nhattruong.financialmanager.interactor.api.ApiManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.interactor.resources.ResourcesManager;
import com.example.nhattruong.financialmanager.interactor.sqlite.SQLiteManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, ApiModule.class,
        PreferModule.class, ResourcesModule.class, SQLiteModule.class})
public interface ApplicationComponent {

    void inject(MainApplication mainApplication);

    void inject(BasePresenter basePresenter);

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    ApiManager getApiManager();

    PreferManager getPreferManager();

    ResourcesManager getResourcesManager();

    SQLiteManager getSQLiteManager();

}
