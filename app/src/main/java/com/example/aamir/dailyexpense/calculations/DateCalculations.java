package com.example.aamir.dailyexpense.calculations;

import android.annotation.TargetApi;
import android.os.Build;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateCalculations {

    Date today;
    Calendar calendar;


    public String getDayOfMonth()
    {
        calendar = Calendar.getInstance();
        return Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public String getLastDayOfMonth()
    {
        calendar = Calendar.getInstance();
        return Integer.toString(calendar.getActualMaximum(Calendar.DATE));
    }

    public String getDateString(String date)
    {
        return date.substring(0,date.indexOf("-"));
    }

    public String getFormattedDate(String date) throws Exception {

        Date dt = new SimpleDateFormat("dd-mm-yyyy").parse(date);
        SimpleDateFormat format = new SimpleDateFormat("E dd-mm-yyyy");
        format.format(dt);
        return (dt.toString()).substring(0, 10);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public LocalDate getFormattedDateInput(String date) throws Exception
    {
        if(date.equalsIgnoreCase("TODAY"))
        {
            return LocalDate.now();
        }
        else
        {
            DateTimeFormatter dtd = DateTimeFormatter.ofPattern("d-M-yyyy");
            LocalDate newDate = LocalDate.parse(date, dtd);
            return (newDate);
        }
    }

    public String getToday()
    {
        Date now = new Date();
        SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy");
        return format.format(now);
    }
}
