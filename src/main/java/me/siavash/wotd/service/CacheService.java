package me.siavash.wotd.service;


import lombok.extern.slf4j.Slf4j;
import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import org.ehcache.Cache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CacheService implements CommandLineRunner {

  private Cache<String, Word> cache;
  private WordRepository repository;

  public CacheService(WordRepository repository,
                      Cache<String, Word> cache){
    this.cache = cache;
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    //TODO: Populate cache on startup;
  }


  public Optional<Word> findById(String date) {
    return null;
  }
}
