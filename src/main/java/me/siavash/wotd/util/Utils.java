package me.siavash.wotd.util;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static HttpStatus validate(String date) {
        if (date.equals("today")) {
            return HttpStatus.OK;
        }
        try{
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            if (validDateRange(localDate)){
                return HttpStatus.OK;
            } else {
                return HttpStatus.BAD_REQUEST;
            }

        }
        catch (DateTimeParseException e){
            return HttpStatus.BAD_REQUEST;
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
