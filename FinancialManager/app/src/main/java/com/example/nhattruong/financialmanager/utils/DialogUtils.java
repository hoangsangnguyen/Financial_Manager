package com.example.nhattruong.financialmanager.utils;

import android.content.Context;

import com.example.nhattruong.financialmanager.R;
import com.example.nhattruong.financialmanager.dialog.DialogOk;
import com.example.nhattruong.financialmanager.dialog.DialogPositiveNegative;
import com.example.nhattruong.financialmanager.dialog.DialogProgress;

public class DialogUtils {

    public static DialogProgress showProgressDialog(Context context) {
        DialogProgress progressDialog = new DialogProgress(context);
        progressDialog.show();
        return progressDialog;
    }

    public static void showOkDialog(Context context, String title, String message, DialogOk.IOkDialogListener listener) {
        DialogOk dialogOk = new DialogOk(context, title, message, listener);
        dialogOk.show();
    }

    public static void showConfirmDialog(Context context, String title, String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogPositiveNegative dialog = new DialogPositiveNegative(context, title, message, context.getString(R.string.ok), context.getString(R.string.cancel));
        dialog.setOnIPositiveNegativeDialogListener(listener);
        dialog.show();
    }
}
