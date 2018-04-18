package com.example.nhattruong.financialmanager.utils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String parseDouble(double amount, int precision) {
        if (amount == 0) {
            return "0.0";
        }
        return String.format(Locale.US, "%1.1f", round(amount, precision));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
