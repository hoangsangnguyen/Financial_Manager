package com.example.nhattruong.financialmanager.sqlite;

import android.database.Cursor;

/**
 * Created by HRC7 on 11/14/2017.
 */

public class Config {
    public static final String USER_TOKEN = MyDatabaseHelper.COL_USER_TOKEN;
    public static final String USER_DATA = MyDatabaseHelper.COL_USER_DATA;
    private final Cursor mCursor;

    public Config(Cursor cursor) {
        this.mCursor = cursor;
        if (cursor != null)
            mCursor.moveToFirst();
    }

    public String get(String key) {
        if (mCursor != null)
            return mCursor.getString(mCursor.getColumnIndex(key));
        return null;
    }
}
