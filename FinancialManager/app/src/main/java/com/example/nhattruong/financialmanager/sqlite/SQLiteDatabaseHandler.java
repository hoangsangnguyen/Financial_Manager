package com.example.nhattruong.financialmanager.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FinancialManagerDB";
    private static final String TABLE_NAME = "FinancialManager";

    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER = "user";

    private static final String[] COLUMNS = new String[]{KEY_TOKEN, KEY_USER};

    public SQLiteDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " (" + KEY_TOKEN + " TEXT, "
                + KEY_USER + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
        createConfig(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public void saveData(String key, String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key, value);
        db.update(TABLE_NAME, values, null, null);
        db.close();
    }

    public String loadData(String key){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex(key));
        }

        return "";
    }

    private void createConfig(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TOKEN, "");
        contentValues.put(KEY_USER, "");
        db.insert(TABLE_NAME, null, contentValues);
    }
}
