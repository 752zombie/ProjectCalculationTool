package com.example.projecttool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

// Matthias

public class DueDateCalculator {

    public String calculateDueDate(int hoursDay, int hoursTotal, String startDate, boolean countWeekends, int numEmps) {
        //You are not allowed to divide by zero
        if (hoursDay <= 0 || numEmps <= 0) {
            throw new ArithmeticException();
        }

        String finalDate;

        // IMPROVED VERSION WITH AMOUNT OF EMPLOYEES //
        int totalWorkHoursDay = hoursDay * numEmps;
        int daysToFinish = hoursTotal / totalWorkHoursDay;


    if (countWeekends) {

        String dueDate = dateAdderWithWeekends(daysToFinish, startDate);

        finalDate = dueDate;

    }
    else {
        LocalDate str = LocalDate.parse(startDate);

        LocalDate withoutWeekends = dateAdderWithoutWeekend(str, daysToFinish);

        String dateWithoutWeekends = withoutWeekends.toString();

        finalDate = dateWithoutWeekends;


    }
        return finalDate;

    }

    private String dateAdderWithWeekends(int daysToFinish, String startDate) {

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            //Setting the date to the given date
            c.setTime(sdf.parse(startDate));
        } catch (
                ParseException e) {
            e.printStackTrace();
        }

        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, daysToFinish);
        //Date after adding the days to the given date
        String dueDate = sdf.format(c.getTime());


        return dueDate;

    }


    private static LocalDate dateAdderWithoutWeekend(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }


}
