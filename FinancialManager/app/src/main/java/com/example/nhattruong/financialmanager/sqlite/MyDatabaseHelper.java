package com.example.nhattruong.financialmanager.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nhattruong on 3/23/2018.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    private static final String DATABASE_NAME = "DATABASE_NAME";
    private static final int DATABASE_VERSION = 1;

    private static final String TB_DATABASE = "TB_DATABASE";
    public static final String COL_USER_TOKEN = "USER_TOKEN";
    public static final String COL_USER_DATA = "USER_DATA";

    private static final String QUERY_CREATE_DATABASE_TABLE = String.format("create table %s (%s text, %s text)",
            TB_DATABASE,
            COL_USER_TOKEN,
            COL_USER_DATA);

    private static final String QUERY_DROP_CONFIG_TABLE = "drop table if exists " + TB_DATABASE;

    public MyDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        sqLiteDatabase.execSQL(QUERY_CREATE_DATABASE_TABLE);
        createConfig(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        sqLiteDatabase.execSQL(QUERY_DROP_CONFIG_TABLE);
        onCreate(sqLiteDatabase);
    }

    public int save(ContentValues contentValues) {
        SQLiteDatabase writeConfig = getWritableDatabase();
        return writeConfig.update(TB_DATABASE, contentValues, null, null);
    }

    public int save(String key, String value) {
        SQLiteDatabase writeConfig = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, value);
        return writeConfig.update(TB_DATABASE, contentValues, null, null);
    }

    private void createConfig(SQLiteDatabase writeConfig) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_DATA, "");
        contentValues.put(COL_USER_TOKEN, "");
        writeConfig.insert(TB_DATABASE, null, contentValues);
    }

    public Cursor load(String... keys) {
        SQLiteDatabase readConfig = getReadableDatabase();
        return readConfig.query(TB_DATABASE, keys, null, null, null, null, null);
    }
}
