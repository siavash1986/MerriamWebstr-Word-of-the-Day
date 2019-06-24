package me.siavash.wotd;

import me.siavash.wotd.entities.Word;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CacheManager getCacheManager(){
    return CacheManagerBuilder.newCacheManagerBuilder()
        .withCache("preConfigured",
            CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Word.class,
                ResourcePoolsBuilder.heap(100))
                .build())
        .build(true);
  }

  @Bean
  public Cache getCache(){
    return getCacheManager().getCache("preConfigured", String.class, Word.class);
  }
}
