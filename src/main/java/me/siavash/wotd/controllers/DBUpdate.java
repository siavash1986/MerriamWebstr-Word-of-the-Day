package me.siavash.wotd.controllers;

import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import me.siavash.wotd.util.Parser;
import me.siavash.wotd.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/word")
public class DBUpdate {

  private final WordRepository repository;

  @Autowired
  public DBUpdate(WordRepository repository) {
    this.repository = repository;
  }


//  @RequestMapping(method = RequestMethod.PUT)
//  public @ResponseBody
//  Response insert(@RequestParam(value = "date", required = false, defaultValue = "today") String date) {
//    HttpStatus httpStatus = Utils.validate(date) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//    if (httpStatus == HttpStatus.OK) {
//
//      new Thread(() -> Utils.downloadPodcast(date)).start();
//
//      Word word = Parser.get(Utils.parseDate(date));
//      Word saved = repository.save(word);
//      if (word.equals(saved)) {
//        return new Response(HttpStatus.OK);
//      } else {
//        return new Response(HttpStatus.INTERNAL_SERVER_ERROR);
//      }
//    } else {
//      return new Response(httpStatus);
//    }
//  }


}
