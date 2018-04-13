package com.example.nhattruong.financialmanager.interactor.prefer;

import android.content.SharedPreferences;

import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.sqlite.Config;
import com.example.nhattruong.financialmanager.sqlite.MyProvider;
import com.google.gson.Gson;

public class PreferManager {

    private SharedPreferences mPreferences;

    public PreferManager(SharedPreferences sharedPreferences) {
        mPreferences = sharedPreferences;
    }

    public String getToken() {
        Config config = MyProvider.load(Config.USER_TOKEN);
        if (config != null) {
            String token = config.get(Config.USER_TOKEN);
            if (token == null) return null;
            if (token.equals("")) return null;
            return token;
        }
        return null;
    }

    public void setToken(String token) {
        MyProvider.save(Config.USER_TOKEN, token);
    }

    // login
    public boolean isLogin() {
        return getToken() != null;
    }

    // logout
    public void resetUser() {
        setToken(null);
        setUser(null);
    }

    //User
    public void setUser(String user) {
        Config config = MyProvider.load(Config.USER_DATA);
        if (config != null) {
            String oldUser = config.get(Config.USER_DATA);
            if (oldUser == null || oldUser.equals("")) oldUser = user;
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(Config.USER_DATA, oldUser);
            editor.apply();
            editor.commit();
        }
        MyProvider.save(Config.USER_DATA, user);
    }

    public User getUser() {
        Config config = MyProvider.load(Config.USER_DATA);
        if (config == null) return new User();
        try {
            return new Gson().fromJson(config.get(Config.USER_DATA), User.class);
        } catch (Exception e) {
            return new User();
        }
    }

}
