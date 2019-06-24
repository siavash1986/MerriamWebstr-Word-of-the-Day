package me.siavash.wotd.service;


import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.ehcache.Cache;

import java.time.LocalDate;


@Service
public class WordsService {

  private WordRepository repository;
  private Cache<String, Word> cache;

  public WordsService(WordRepository repository, Cache<String, Word> cache){
    this.repository = repository;
    this.cache = cache;
  }

  public Word getWord(LocalDate parse) {
    return null;
  }

  @Scheduled(cron = "* * * * *")
  public void update(){

  }
}
