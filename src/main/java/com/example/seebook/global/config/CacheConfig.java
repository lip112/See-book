package com.example.seebook.global.config;

import org.ehcache.config.builders.*;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        // JCache CachingProvider 생성
        CachingProvider provider = Caching.getCachingProvider();
        javax.cache.CacheManager cacheManager = provider.getCacheManager();

        // homeReviewList 캐시 설정 (TTL: 60초)
        org.ehcache.config.CacheConfiguration<Object, Object> homeReviewListConfig =
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                Object.class, Object.class,
                                ResourcePoolsBuilder.heap(10))  // 최대 10개의 엔트리 저장
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(60)))  // TTL: 60초
                        .build();

        // bookTextSearch 캐시 설정 (TTL 300초, TTI 60초 적용)
        org.ehcache.config.CacheConfiguration<Object, Object> bookTextSearchConfig =
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                Object.class, Object.class,
                                ResourcePoolsBuilder.heap(200))  // 최대 200개의 엔트리 저장
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(60))) //TTL
                        .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(300))) //TTI
                        .build();

        cacheManager.createCache("homeReviewList",
                Eh107Configuration.fromEhcacheCacheConfiguration(homeReviewListConfig));

        cacheManager.createCache("bookTextSearch",
                Eh107Configuration.fromEhcacheCacheConfiguration(bookTextSearchConfig));

        return new JCacheCacheManager(cacheManager);
    }
}