package com.example.nhattruong.financialmanager.interactor.prefer;

import android.content.SharedPreferences;

import com.example.nhattruong.financialmanager.model.User;
import com.google.gson.Gson;

public class PreferManager {

    private SharedPreferences mPreferences;

    public PreferManager(SharedPreferences sharedPreferences) {
        mPreferences = sharedPreferences;
    }

    // Token
    private static final String KEY_TOKEN = "TOKEN";

    public String getToken() {
        return mPreferences.getString(KEY_TOKEN, null);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    //User
    private static final String KEY_USER = "KEY_USER";

    public void setUser(User user) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_USER, new Gson().toJson(user));
        editor.apply();
    }

    public User getUser() {
        User user = null;
        try {
            user = new Gson().fromJson(mPreferences.getString(KEY_USER, new Gson().toJson(new User())), User.class);
        } catch (Exception ignored) {
        }
        return user;
    }

    // logout
    public void resetUser() {
        setToken(null);
        setUser(null);
    }

    //url
    private static final String KEY_BASE_URL = "KEY_BASE_URL";

    public String getBaseUrl() {
        return mPreferences.getString(KEY_BASE_URL, null);
    }

    public void setBaseUrl(String baseUrl) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_BASE_URL, baseUrl);
        editor.apply();
    }

    // ======== [END] HELPER ========

}
