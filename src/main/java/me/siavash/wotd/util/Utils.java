package me.siavash.wotd.util;

import me.siavash.wotd.entities.Word;
import me.siavash.wotd.entities.FlatWord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

public class Utils {

    private Utils(){}

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

    public static boolean validate(String begin, String end) {
        return validate(begin) && validate(end)
                && !LocalDate.parse(parseDate(begin)).isAfter(LocalDate.parse(parseDate(end)));
    }

    private static boolean validDateRange(LocalDate localDate){
        return (!(localDate.isBefore(LocalDate.of(2006, 10,25)) ||
                localDate.isAfter(LocalDate.now())));
    }

    public static String parseDate(String date) {
        return date.equals("today") ? LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                : date;
    }

    public static FlatWord flatWordAdapter(Word w) {
        FlatWord flatWord = new FlatWord();
        flatWord.setDate(w.getDate());
        flatWord.setTitle(w.getTitle() != null? w.getTitle() : "");
        flatWord.setAttribute(w.getAttribute() != null ? w.getAttribute() : "");
        flatWord.setSyllables(w.getSyllables() != null ? w.getSyllables() : "");
        flatWord.setDefinition(w.getDefinition() != null? w.getDefinition().stream().collect(Collectors.joining(System.getProperty("line.separator"))) : "");
        flatWord.setExamples(w.getExamples() != null? w.getExamples().stream().collect(Collectors.joining(System.getProperty("line.separator"))) : "");
        flatWord.setDidYouKnow(w.getDidYouKnow() != null ? w.getDidYouKnow() : "");
        flatWord.setPodcastUrl(w.getPodcastUrl() != null ? w.getPodcastUrl() : "");
        flatWord.setImageUrl(w.getImageUrl() != null ? w.getImageUrl() : "");
        return flatWord;
    }
}
