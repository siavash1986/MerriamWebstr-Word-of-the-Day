package me.siavash.wotd.controllers;


import me.siavash.wotd.Response;
import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/word")

public class WordController {

    private final WordRepository repository;

    @Autowired
    public WordController(WordRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Response getWord(@RequestParam(value="date", required=false, defaultValue="today") String date) {
        HttpStatus httpStatus = validate(date);
        if (httpStatus == HttpStatus.OK){
            Word one = repository.findWordByDate(parseDate(date));
            return new Response(one, httpStatus);
        } else {
            return new Response(httpStatus);
        }
    }

    private HttpStatus validate(String date) {
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

    private boolean validDateRange(LocalDate localDate){
        return (!(localDate.isBefore(LocalDate.of(2006, 10,25)) ||
                localDate.isAfter(LocalDate.now())));
    }

    private LocalDate parseDate(String date) {
        return date.equals("today") ? LocalDate.now() : LocalDate.parse(date);
    }
}
