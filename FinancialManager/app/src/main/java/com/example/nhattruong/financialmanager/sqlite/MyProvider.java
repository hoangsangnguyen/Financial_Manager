package com.example.nhattruong.financialmanager.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nhattruong.financialmanager.R;

public class MyProvider extends ContentProvider {
    private static final String PATH = "gito_shared";
    private static final String TYPE_VALID = "TYPE_VALID";
    private static final int NUM_OF_ARGS = 1;
    private static Context sContext;

    private MyDatabaseHelper mConfig;
    private UriMatcher mURIMatcher;

    @Override
    public boolean onCreate() {
        sContext = getContext();
        mURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mURIMatcher.addURI(getProviderName(), PATH, NUM_OF_ARGS);
        mConfig = new MyDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (mURIMatcher.match(uri)) {
            case NUM_OF_ARGS:
                cursor = mConfig.load(projection);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI " + uri.toString());
        }
        if (cursor != null)
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (mURIMatcher.match(uri)) {
            case NUM_OF_ARGS:
                break;
            default:
                throw new IllegalArgumentException("Unknow URI " + uri.toString());
        }
        return TYPE_VALID;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String key, @Nullable String[] values) {
        if (contentValues != null)
            return mConfig.save(contentValues);
        return -1;
    }

    public static Uri getUri() {
        return Uri.parse("content://" + sContext.getString(R.string.provider_name) + "/" + PATH);
    }

    public static void save(String key, String value) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, value == null ? "" : value);
        if (getUriRegistered()) {
            sContext.getContentResolver().update(getUri(), contentValues, null, null);
        }
    }

    private static void save(Uri uri, ContentValues contentValues) {
        sContext.getContentResolver().update(uri, contentValues, null, null);
    }

    private static Config load(Uri uri, String... keys) {
        return new Config(sContext.getContentResolver().query(uri, keys, null, null, null));
    }

    public static Config load(String... keys) {
        if (getUri() != null)
            return load(getUri(), keys);
        return null;
    }

    private static boolean isAuthoritiesRegistered() {
        Uri uri = getUri();
        String type = sContext.getContentResolver().getType(uri);
        if (type != null && type.equals(TYPE_VALID)) return true;
        return false;
    }

    private static boolean getUriRegistered() {
        return isAuthoritiesRegistered();
    }

    private static String getProviderName() {
        return sContext.getString(R.string.provider_name);
    }

}
