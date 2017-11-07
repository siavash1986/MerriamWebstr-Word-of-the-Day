package me.siavash.wotd.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static boolean validate(String date) {
        if (date.equals("today")) {
            return true;
        }
        try{
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return validDateRange(localDate);

        }
        catch (DateTimeParseException e){
            return false;
        }
    }

    private static boolean validDateRange(LocalDate localDate){
        return (!(localDate.isBefore(LocalDate.of(2006, 10,25)) ||
                localDate.isAfter(LocalDate.now())));
    }

    public static String parseDate(String date) {
        return date.equals("today") ? LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                : date;
    }
}
