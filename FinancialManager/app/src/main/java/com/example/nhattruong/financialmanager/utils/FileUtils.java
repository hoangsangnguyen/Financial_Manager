package com.example.nhattruong.financialmanager.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import com.example.nhattruong.financialmanager.BuildConfig;

import java.io.File;

public class FileUtils {

    public static void startActionGetImage(Activity activity, int requestCode, String chooserTitle) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, chooserTitle), requestCode);
    }

    private static Uri getUriFromFile(Context context, File file) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
        }
        return Uri.fromFile(file);
    }

    public static File convertUriToFile(Context context, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};

        try {
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            assert cursor != null;
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            File file = new File(cursor.getString(index));
            cursor.close();
            return file;

        } catch (Exception e) {
            return new File(uri.getPath());
        }
    }

    public static String getMineType(Context context, File file) {
        String mineType = null;

        if (file.getName().lastIndexOf(".") != -1) {
            String ext = file.getPath().substring(file.getPath().lastIndexOf('.') + 1);
            mineType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext.toLowerCase());
        }
        return mineType;
    }

}
