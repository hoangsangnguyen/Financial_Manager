package com.example.nhattruong.financialmanager.injection.module;



import com.example.nhattruong.financialmanager.interactor.event.EventManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EventModule {

    @Provides
    @Singleton
    EventManager provideEventManager() {
        return new EventManager();
    }

}
