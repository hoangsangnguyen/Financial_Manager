package com.example.nhattruong.financialmanager.interactor.api.network;


public interface  SocketCallback<T>{

      void success(T res);

      void failure(String error);

}
