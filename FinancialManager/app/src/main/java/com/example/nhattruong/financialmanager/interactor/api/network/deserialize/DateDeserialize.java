package com.example.nhattruong.financialmanager.interactor.api.network.deserialize;

import com.example.nhattruong.financialmanager.utils.AppConstants;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserialize implements
        JsonDeserializer<Date> {

    private final DateFormat serverDateFormat;

    public DateDeserialize() {
        serverDateFormat = new SimpleDateFormat(AppConstants.SERVER_DATE_FORMAT, Locale.US);
        serverDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            return serverDateFormat.parse(jsonElement.getAsString());
        } catch (ParseException e) {
            return null;
        }
    }
}
