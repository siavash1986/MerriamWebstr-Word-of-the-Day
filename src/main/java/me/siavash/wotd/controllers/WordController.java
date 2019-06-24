package me.siavash.wotd.controllers;


import me.siavash.wotd.entities.Word;
import me.siavash.wotd.service.WordsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;


@Controller
@RequestMapping("/api")
public class WordController {

  private final WordsService service;

  public WordController(WordsService service) {
    this.service = service;
  }

  @GetMapping("/word/{date}")
  public Word getWord(@PathVariable(name = "date", required = false) String date){
    return StringUtils.isEmpty(date) ?
        service.getWord(LocalDate.now(ZoneId.of("UTC")).toString()) :
        service.getWord(date);
  }

  @GetMapping("/words")
  public Set<Word> getWords(@RequestParam(value = "startDate") String startDate,
                            @RequestParam(value = "endDate") String endDate) {
    return service.getWords(startDate, endDate);
  }

}
