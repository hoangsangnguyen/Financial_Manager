package com.example.nhattruong.financialmanager.interactor.api.network;

import android.content.Context;

import com.example.nhattruong.financialmanager.R;

public class APIErrorUtils {
    public static final int API_ERROR_NO_NETWORK = -1;
    public static final int API_ERROR_TIMED_OUT = -2;
    public static final int API_ERROR_UNKNOWN = -4;

    public static String getErrorMessage(Context context, int errorCode) {
        switch (errorCode) {
            case API_ERROR_NO_NETWORK:
                return context.getString(R.string.api_err_no_network);
            case API_ERROR_TIMED_OUT:
                return context.getString(R.string.api_err_time_out);
            case API_ERROR_UNKNOWN:
                return context.getString(R.string.api_err_unknown_error);
        }
        return "";
    }

}
