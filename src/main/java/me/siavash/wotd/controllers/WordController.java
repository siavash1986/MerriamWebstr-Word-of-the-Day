package me.siavash.wotd.controllers;


import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import me.siavash.wotd.service.WordsService;
import me.siavash.wotd.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Controller
@RequestMapping("/api")
public class WordController {

  private final WordsService service;

  public WordController(WordsService service) {
    this.service = service;
  }

  @GetMapping("/word/{date}")
  public Word getWord(@PathVariable("date") String date){
    return StringUtils.isEmpty(date) ?
        service.getWord(LocalDate.now(ZoneId.of("UTC"))) :
        service.getWord(LocalDate.parse(date));
  }

//  @RequestMapping(method = RequestMethod.GET, path = "/word")
//  public Response<Word> getWord(@RequestParam(value = "date", required = false, defaultValue = "today") String date) {
//    HttpStatus httpStatus = Utils.validate(date) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//    if (httpStatus == HttpStatus.OK) {
//      Word one = repository.findWordByDate(Utils.parseDate(date));
//      return new Response<>(one, httpStatus);
//    } else {
//      return new Response<>(httpStatus);
//    }
//  }

//  @RequestMapping(method = RequestMethod.GET, path = "/word/flat")
//  public Response<FlatWord> getFlatWord(@RequestParam(value = "date", required = false, defaultValue = "today") String date) {
//    HttpStatus httpStatus = Utils.validate(date) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//    if (httpStatus == HttpStatus.OK) {
//      FlatWord one = repository.findFlatWordByDate(Utils.parseDate(date));
//      return new Response<>(one, httpStatus);
//    } else {
//      return new Response<>(httpStatus);
//    }
//  }


}
