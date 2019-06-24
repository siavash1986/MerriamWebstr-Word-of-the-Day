package me.siavash.wotd.repositories;

import me.siavash.wotd.entities.Word;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WordRepository extends CrudRepository<Word, String> {


//
//  default Word findWordByDate(String date) {
//    if (wordsCache.containsKey(date)) {
//      return wordsCache.get(date);
//    } else {
//      Optional<Word> word = findById(date);
//      word.ifPresent(value -> wordsCache.put(date, value));
//      return word.orElse(null);
//    }
//
//  }

//  default Map<String, Word> findWordsByDateRange(String dateBegin, String dateEnd) {
//    List<String> dateRange = new ArrayList<>();
//    LocalDate beginDate = LocalDate.parse(dateBegin);
//    LocalDate endDate = LocalDate.parse(dateEnd);
//    while (beginDate.isBefore(endDate.plusDays(1))) {
//      dateRange.add(beginDate.format(DateTimeFormatter.ISO_DATE));
//      beginDate = beginDate.plusDays(1);
//    }
//
//    List<Word> all = findAll(dateRange);
//    Map<String, Word> wordMap = new HashMap<>();
//    all.parallelStream().forEach(word -> wordMap.put(word.getDate(), word));
//
//    return wordMap;
//
//    // This syntax is also possible but runs much slower.
//    //return findAll(dateRange).parallelStream().collect(Collectors.toMap(Word::getDate, Function.identity()));
//  }

//  @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
//  default Map<String, FlatWord> findFlatWordsByDateRange(String dateBegin, String dateEnd) {
//    Map<String, Word> wordsByDateRange = findWordsByDateRange(dateBegin, dateEnd);
//    Map<String, FlatWord> flatWordMap = new HashMap<>();
//    wordsByDateRange.keySet().forEach(key -> flatWordMap.put(key, Utils.flatWordAdapter(wordsByDateRange.get(key))));
//    return flatWordMap;
//  }
}
