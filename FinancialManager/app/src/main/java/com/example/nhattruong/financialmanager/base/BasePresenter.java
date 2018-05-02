package com.example.nhattruong.financialmanager.base;

import com.example.nhattruong.financialmanager.MainApplication;
import com.example.nhattruong.financialmanager.interactor.api.ApiManager;
import com.example.nhattruong.financialmanager.interactor.assets.AssetsManager;
import com.example.nhattruong.financialmanager.interactor.caches.CachesManager;
import com.example.nhattruong.financialmanager.interactor.event.EventManager;
import com.example.nhattruong.financialmanager.interactor.prefer.PreferManager;
import com.example.nhattruong.financialmanager.interactor.resources.ResourcesManager;
import com.example.nhattruong.financialmanager.interactor.sqlite.SQLiteManager;

import javax.inject.Inject;

import butterknife.Unbinder;

public abstract class BasePresenter {

    private IBaseView mView;

    public IBaseView getView() {
        if (mView == null) {
            throw new IllegalStateException("Presenter must be attach IView");
        }
        return mView;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    @Inject
    ApiManager mApiManager;

    @Inject
    EventManager mEventManager;

    @Inject
    PreferManager mPreferManager;

    @Inject
    AssetsManager mAssetsManager;

    @Inject
    CachesManager mCachesManager;

    @Inject
    ResourcesManager mResourcesManager;

    @Inject
    SQLiteManager mSQLiteManager;

    public BasePresenter() {
        MainApplication.getAppComponent().inject(this);
    }

    public ApiManager getApiManager() {
        return mApiManager;
    }

    public EventManager getEventManager() {
        return mEventManager;
    }

    public PreferManager getPreferManager() {
        return mPreferManager;
    }

    public AssetsManager getAssetsManager() {
        return mAssetsManager;
    }

    public CachesManager getCachesManager() {
        return mCachesManager;
    }

    public ResourcesManager getResourcesManager() {
        return mResourcesManager;
    }

    public SQLiteManager getSQLiteManager(){return mSQLiteManager;}

    private Unbinder mBinder;

    public void onCreate(IBaseView view, Unbinder binder) {
        mView = view;
        mBinder = binder;
    }

    public void onDestroy() {
        mView = null;
        if (mBinder != null)
            mBinder.unbind();
    }
}

