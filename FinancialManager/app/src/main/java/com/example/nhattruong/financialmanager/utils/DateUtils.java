package com.example.nhattruong.financialmanager.utils;

import com.example.nhattruong.financialmanager.mvp.create.fragment.dto.CalendarDto;

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
        return formatDate(date, "dd/MM/yyyy");
    }

    public static String formatDateFilter(Date date) {
        return formatDate(date, "yyyy-MM-dd");
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

    // get current day in month as number
    public static int getCurrentDayInMonth() {
        Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    // get current month as number
    public static int getCurrentMonth() {
        Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    // get current year as number
    public static int getCurrentYear() {
        Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (day < 1 || day > 31) {
            return false;
        }
        if (month < 0 || month > 11) {
            return false;
        }
        if (year < 0) {
            return false;
        }
        if (day < 29) {
            return true;
        }
        switch (month) {
            case 1:
                return day == 29 && isLeapYear(year);
            case 3:
            case 5:
            case 8:
            case 10:
                return day != 31;
            default:
                return true;
        }
    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    // get year of date as number
    public static int getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    // get month of date as number
    public static int getMonthOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    // get day of date as number
    public static int getDayOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    // get first date of date
    public static Date getFirstDateOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // reset time
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date resetDateEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        return calendar.getTime();
    }

    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public static int compareDate(Date date1, Date date2){
        SimpleDateFormat math = new SimpleDateFormat("yyyyMMdd");
        Long date1asLong = new Long(math.format(date1));
        Long date2asLong = new Long(math.format(date2));
        return date1asLong.compareTo(date2asLong);
    }
}
