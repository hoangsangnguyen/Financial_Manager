package com.example.nhattruong.financialmanager.interactor.api.network;

import com.example.nhattruong.financialmanager.interactor.api.network.deserialize.DateDeserialize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class GsonUtils {
    public static Gson createGson(Class exClazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();

       /* if (exClazz != User.class) {
            gsonBuilder.registerTypeAdapter(User.class, new UserDeserialize());
        }*/

        gsonBuilder.registerTypeHierarchyAdapter(Date.class, new DateDeserialize());
        return gsonBuilder.create();
    }

}
