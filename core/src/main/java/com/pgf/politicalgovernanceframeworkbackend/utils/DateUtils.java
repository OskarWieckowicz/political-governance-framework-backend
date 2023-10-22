package com.pgf.politicalgovernanceframeworkbackend.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {
    public static String getLastBillingPeriod() {
        LocalDate currentDate = LocalDate.now();

        // Calculate the last month
        LocalDate lastMonthDate = currentDate.minusMonths(1);

        // Format the last month and year as "MM.yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
        return lastMonthDate.format(formatter);
    }


    public static String getCurrentBillingPeriod() {
        LocalDate currentDate = LocalDate.now();

        // Format the last month and year as "MM.yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
        return currentDate.format(formatter);
    }
}
