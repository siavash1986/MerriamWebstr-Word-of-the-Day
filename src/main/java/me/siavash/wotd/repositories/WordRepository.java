package me.siavash.wotd.repositories;

import me.siavash.wotd.entities.Word;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WordRepository extends JpaRepository<Word, LocalDate> {

    static CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("preConfigured",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(LocalDate.class, Word.class,
                            ResourcePoolsBuilder.heap(100))
                            .build())
            .build(true);

    static Cache<LocalDate, Word> wordsCache = cacheManager.getCache("preConfigured", LocalDate.class, Word.class);

    public default Word findWordByDate(LocalDate date){
        if (wordsCache.containsKey(date)){
            return wordsCache.get(date);
        } else{
            Word word =  getOne(date);
            wordsCache.put(date, word);
            return word;
        }

    }
}
