package me.siavash.wotd.controllers;

import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import me.siavash.wotd.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class Update {

//  private WordRepository repo;
//
//  @Autowired
//  public Update(WordRepository repo) {
//    this.repo = repo;
//  }

  @ResponseBody
  @PostMapping("/update")
  public String update(@RequestParam(value = "startDate") String startDate,
                       @RequestParam(value = "endDate") String endDate) {

//    Map<String, Word> wordsByDateRange = repo.findWordsByDateRange(startDate, endDate);
//
//    wordsByDateRange.values().parallelStream().forEach(w -> {
//      if (w.getPronounceUrl().equals("")) {
//        w.setPronounceUrl(Utils.getPronounceUrl(w));
//        repo.saveAndFlush(w);
//      }
//    });
//
////    wordsByDateRange.forEach((k, w) -> {
////      if (w.getPronounceUrl().equals("")) {
////        w.setPronounceUrl(Utils.getPronounceUrl(w));
////        repo.saveAndFlush(w);
////      }
////
////    });
//
//    return "success!";
    return null;
  }
}
