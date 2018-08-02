package com.example.nhattruong.financialmanager.interactor.sqlite;

import android.content.Context;
import android.text.TextUtils;

import com.example.nhattruong.financialmanager.model.Todo;
import com.example.nhattruong.financialmanager.model.Type;
import com.example.nhattruong.financialmanager.model.User;
import com.example.nhattruong.financialmanager.sqlite.SQLiteDatabaseHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SQLiteManager {

    private Context mContext;
    private SQLiteDatabaseHandler mDatabase;

    public SQLiteManager(Context context){
        this.mContext = context;
        mDatabase = new SQLiteDatabaseHandler(mContext);
    }

    public void saveToken(String value){
        mDatabase.saveData(SQLiteDatabaseHandler.KEY_TOKEN, value);
    }

    public String getToken(){
        return mDatabase.loadData(SQLiteDatabaseHandler.KEY_TOKEN);
    }

    public void saveUser(String value){
        mDatabase.saveData(SQLiteDatabaseHandler.KEY_USER, value);
    }

    public User getUser(){
        if (TextUtils.isEmpty(mDatabase.loadData(SQLiteDatabaseHandler.KEY_USER))) return new User();
        try {
            return new Gson().fromJson(mDatabase.loadData(SQLiteDatabaseHandler.KEY_USER), User.class);
        } catch (Exception e) {
            return new User();
        }
    }

    public void saveTodoList(List<Todo> items){
        mDatabase.saveData(SQLiteDatabaseHandler.KEY_TODO, new Gson().toJson(items));
    }

    public List<Todo> getTodoList(){
        if (TextUtils.isEmpty(mDatabase.loadData(SQLiteDatabaseHandler.KEY_TODO))) return new ArrayList<>();
        try {
            String json = mDatabase.loadData(SQLiteDatabaseHandler.KEY_TODO);
            java.lang.reflect.Type type = new TypeToken<ArrayList<Todo>>(){}.getType();
            return new Gson().fromJson(json, type);
        }catch (Exception e) {
           return new ArrayList<>();
        }
    }

    // login
    public boolean isLogin() {
        return getToken() != null;
    }

    // logout
    public void resetUser() {
        saveToken(null);
        saveUser(null);
    }
}
