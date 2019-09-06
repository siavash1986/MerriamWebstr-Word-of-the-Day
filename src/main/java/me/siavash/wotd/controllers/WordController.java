package me.siavash.wotd.controllers;


import me.siavash.wotd.Response;
import me.siavash.wotd.entities.FlatWord;
import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import me.siavash.wotd.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class WordController {

  @GetMapping("/word/{date}")
  public Word getWord(@PathVariable(required = false) String date) {
    return new Word();
  }







//
//  private final WordRepository repository;
//
//  public WordController(WordRepository repository) {
//    this.repository = repository;
//  }

  @RequestMapping(method = RequestMethod.GET, path = "/word")
  public ResponseEntity<Word> getWord2(@RequestParam(value = "date", required = false, defaultValue = "today") String date) {
//    HttpStatus httpStatus = Utils.validate(date) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//    if (httpStatus == HttpStatus.OK) {
//      Word one = repository.findWordByDate(Utils.parseDate(date));
//      return new Response<>(one, httpStatus);
//    } else {
//      return new Response<>(httpStatus);
//    }
    return ResponseEntity.ok().build();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/word/flat")
  public Response<FlatWord> getFlatWord(@RequestParam(value = "date", required = false, defaultValue = "today") String date) {
//    HttpStatus httpStatus = Utils.validate(date) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//    if (httpStatus == HttpStatus.OK) {
//      FlatWord one = repository.findFlatWordByDate(Utils.parseDate(date));
//      return new Response<>(one, httpStatus);
//    } else {
//      return new Response<>(httpStatus);
//    }
    return null;
  }


}
