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
import com.example.nhattruong.financialmanager.injection.module.AssetsModule;
import com.example.nhattruong.financialmanager.injection.module.CachesModule;
import com.example.nhattruong.financialmanager.injection.module.EventModule;
import com.example.nhattruong.financialmanager.injection.module.NetworkModule;
import com.example.nhattruong.financialmanager.injection.module.PreferModule;
import com.example.nhattruong.financialmanager.injection.module.ResourcesModule;
import com.example.nhattruong.financialmanager.interactor.api.ApiManager;
import com.example.nhattruong.financialmanager.interactor.assets.AssetsManager;
import com.example.nhattruong.financialmanager.interactor.caches.CachesManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.interactor.resources.ResourcesManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, ApiModule.class, EventModule.class,
        PreferModule.class, AssetsModule.class, CachesModule.class, ResourcesModule.class})
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

    AssetsManager getAssetManager();

    CachesManager getCachesManager();

    ResourcesManager getResourcesManager();

}
