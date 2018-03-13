package com.example.nhattruong.financialmanager.interactor.event;

import com.example.nhattruong.financialmanager.interactor.event.type.InvalidTokenType;

import org.greenrobot.eventbus.EventBus;

public class EventManager {

    public void register(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public void unRegister(Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public void sendEvent(InvalidTokenType event) {
        EventBus.getDefault().post(event);
    }

}
