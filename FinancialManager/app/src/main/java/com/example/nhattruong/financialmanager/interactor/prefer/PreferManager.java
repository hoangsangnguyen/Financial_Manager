package com.example.nhattruong.financialmanager.interactor.prefer;

import android.content.SharedPreferences;

public class PreferManager {

    private SharedPreferences mPreferences;

    public PreferManager(SharedPreferences sharedPreferences) {
        mPreferences = sharedPreferences;
    }

    // FCM token
    private static final String KEY_FCM_TOKEN = "KEY_FCM_TOKEN";

    public String getFcmToken() {
        return mPreferences.getString(KEY_FCM_TOKEN, null);
    }

    public void setFcmToken(String fcmToken) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_FCM_TOKEN, fcmToken);
        editor.apply();
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

   /* public void setUser(User user) {
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
    }*/

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
