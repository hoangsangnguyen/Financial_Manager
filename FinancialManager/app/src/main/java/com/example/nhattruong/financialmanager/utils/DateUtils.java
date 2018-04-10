package com.example.nhattruong.financialmanager.utils;

import com.example.nhattruong.financialmanager.mvp.overlap.fragment.dto.CalendarDto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    // get list day of week by start day of week
    public static List<Integer> daysOfWeek(int startDayOfWeek) {
        List<Integer> defaultList = new ArrayList<>();
        defaultList.add(AppConstants.DAY_MONDAY);
        defaultList.add(AppConstants.DAY_TUESDAY);
        defaultList.add(AppConstants.DAY_WEDNESDAY);
        defaultList.add(AppConstants.DAY_THURSDAY);
        defaultList.add(AppConstants.DAY_FRIDAY);
        defaultList.add(AppConstants.DAY_SATURDAY);
        defaultList.add(AppConstants.DAY_SUNDAY);
        int offset = 0;
        for (int i = 0; i < defaultList.size(); i++) {
            if (startDayOfWeek == defaultList.get(i)) {
                offset = i;
                break;
            }
        }
        for (int i = 0; i < offset; i++) {
            defaultList.add(defaultList.get(i));
        }
        for (int i = 0; i < offset; i++) {
            defaultList.remove(0);
        }
        return defaultList;
    }

    public static Date createDateFromDMY(int date, int month, int year) {
        Calendar cal = Calendar.getInstance();

        // reset time
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);

        cal.set(Calendar.DATE, date);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        return cal.getTime();
    }

    // check 2 date is same day
    public static boolean isSameDay(Date leftDate, Date rightDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(leftDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(rightDate);
        return startCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR) &&
                startCalendar.get(Calendar.DAY_OF_YEAR) == endCalendar.get(Calendar.DAY_OF_YEAR);
    }

    // get app constant day from calendar day (in Calendar)
    public static int getDayFromCalendarDay(int calendarDay) {
        switch (calendarDay) {
            case Calendar.SUNDAY:
                return AppConstants.DAY_SUNDAY;
            case Calendar.MONDAY:
                return AppConstants.DAY_MONDAY;
            case Calendar.TUESDAY:
                return AppConstants.DAY_TUESDAY;
            case Calendar.WEDNESDAY:
                return AppConstants.DAY_WEDNESDAY;
            case Calendar.THURSDAY:
                return AppConstants.DAY_THURSDAY;
            case Calendar.FRIDAY:
                return AppConstants.DAY_FRIDAY;
            case Calendar.SATURDAY:
                return AppConstants.DAY_SATURDAY;
        }
        return AppConstants.DAY_SUNDAY;
    }

    // get number of day before a start day of week
    public static int getBeforeRemainDayOfMonth(int minDayOfMonth) {
//        List<Integer> dayOfWeek = daysOfWeek(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        List<Integer> dayOfWeek = daysOfWeek(AppConstants.DAY_SUNDAY);
        for (int i = 0; i < dayOfWeek.size(); i++) {
            if (minDayOfMonth == dayOfWeek.get(i)) {
                return i;
            }
        }
        return 0;
    }

    // get number of day after a start day of week
    public static int getAfterRemainDayOfMonth(int maxDayOfMonth) {
//        List<Integer> dayOfWeek = daysOfWeek(Calendar.getInstance().get(Calendar.DATE));
        List<Integer> dayOfWeek = daysOfWeek(AppConstants.DAY_SUNDAY);
        for (int i = 0; i < dayOfWeek.size(); i++) {
            if (maxDayOfMonth == dayOfWeek.get(i)) {
                return dayOfWeek.size() - 1 - i;
            }
        }
        return dayOfWeek.size() - 1;
    }

    public static String getDisplayNameOfMonth(int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month);
        return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    }

    public static boolean isCurrentDay(CalendarDto calendarDto, CalendarDto currentDate) {
        Date dateObj = DateUtils.createDateFromDMY(calendarDto.getDate(), calendarDto.getMonth(), calendarDto.getYear());
        Date currentDateObj = DateUtils.createDateFromDMY(currentDate.getDate(), currentDate.getMonth(), currentDate.getYear());
        return DateUtils.isSameDay(dateObj, currentDateObj);
    }

    public static String formatFullDatePeriods(Date date) {
        return formatDate(date, "MM/dd/yyyy");
    }


    // format date object to string with a format
    private static String formatDate(Date date, String desFormat) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateResultFormat = new SimpleDateFormat(desFormat, Locale.US);
        dateResultFormat.setTimeZone(TimeZone.getDefault());
        return dateResultFormat.format(date);
    }
}
