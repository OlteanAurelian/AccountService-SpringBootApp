package com.aurelian.application;

import org.springframework.cache.annotation.CacheEvict;

public class CacheService {
    @CacheEvict(value = "exchangeRate", allEntries = true)
    public void evictAllCacheValues() {}
}