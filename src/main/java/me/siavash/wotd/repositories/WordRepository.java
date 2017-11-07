package me.siavash.wotd.repositories;

import me.siavash.wotd.entities.Word;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface WordRepository extends JpaRepository<Word, String> {

    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("preConfigured",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Word.class,
                            ResourcePoolsBuilder.heap(100))
                            .build())
            .build(true);

    Cache<String, Word> wordsCache = cacheManager.getCache("preConfigured", String.class, Word.class);

    default Word findWordByDate(String date){
        if (wordsCache.containsKey(date)){
            return wordsCache.get(date);
        } else{
            Word word =  getOne(date);
            wordsCache.put(date, word);
            return word;
        }

    }

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    default Map<String, Word> findWordsByDateRange(String dateBegin, String dateEnd){
        List<String> dateRange = new ArrayList<>();
        LocalDate beginDate = LocalDate.parse(dateBegin);
        LocalDate endDate  = LocalDate.parse(dateEnd);
        while (beginDate.isBefore(endDate.plusDays(1))){
            dateRange.add(beginDate.format(DateTimeFormatter.ISO_DATE));
            beginDate = beginDate.plusDays(1);
        }

        List<Word> all = findAll(dateRange);
        Map<String, Word> wordMap = new HashMap<>();
        all.parallelStream().forEach(word -> wordMap.put(word.getDate(), word));

        return wordMap;

        // This syntax is also possible but runs much slower.
        //return findAll(dateRange).parallelStream().collect(Collectors.toMap(Word::getDate, Function.identity()));
    }
}
