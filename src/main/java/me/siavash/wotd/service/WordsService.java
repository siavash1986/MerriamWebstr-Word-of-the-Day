package me.siavash.wotd.service;


import me.siavash.wotd.entities.Word;
import me.siavash.wotd.exception.InvalidDateException;
import me.siavash.wotd.repositories.WordRepository;
import org.springframework.stereotype.Service;
import org.ehcache.Cache;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;


@Service
public class WordsService {

  private CacheService cacheService;

  public WordsService(CacheService cacheService){
    this.cacheService = cacheService;
  }

  public Word getWord(String date) {
    validate(date);
    return cacheService.findById(date).orElseThrow(() -> new EntityNotFoundException("No resource found for {" + date + "}"));
  }

  public Set<Word> getWords(String startDate, String endDate) {
    validateRange(startDate, endDate);
    return null;
  }


  private void validate(String... date) {
    try {
      for (String d : date) {
        LocalDate.parse(d, DateTimeFormatter.ISO_DATE);
      }
    } catch (DateTimeParseException e) {
      throw new InvalidDateException("The provided parameter could not parsed with ISO_LOCAL_DATE, acceptable format example: '2011-12-03'", e);
    }
  }

  private void  validateRange(String begin, String end) {
    validate(begin, end);
    validDateRange(LocalDate.parse(begin, DateTimeFormatter.ISO_DATE), LocalDate.parse(end, DateTimeFormatter.ISO_DATE));
  }

  private void validDateRange(LocalDate begin, LocalDate end) {
    if (!(begin.isBefore(LocalDate.of(2006, 8, 31)) || end.isAfter(LocalDate.now().plusDays(1)))) {
      throw new InvalidDateException("Parameters must be within '2006-08-31' and current date range");
    }
    if (begin.isAfter(end)) {
      throw new InvalidDateException("startDate must be before endDate");
    }
  }
}
