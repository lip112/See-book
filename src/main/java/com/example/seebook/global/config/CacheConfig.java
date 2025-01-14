package com.example.seebook.global.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URL;
import java.net.URISyntaxException;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() throws URISyntaxException {
        CachingProvider provider = Caching.getCachingProvider();
        URL ehcacheConfig = getClass().getResource("/ehcache.xml");
        javax.cache.CacheManager cacheManager = provider.getCacheManager(ehcacheConfig.toURI(), getClass().getClassLoader());
        return new JCacheCacheManager(cacheManager);
    }
}
