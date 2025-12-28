package com.idhanwal.urlshortener.service;

import com.idhanwal.urlshortener.model.UrlMapping;
import com.idhanwal.urlshortener.repository.UrlMappingRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

/**
 * Handles redirect logic using Cache-Aside pattern.
 */
@Service
public class RedirectService {

    private final UrlMappingRepository repository;
    private final RedisTemplate<String, String> redisTemplate;

    private static final Duration CACHE_TTL = Duration.ofHours(24);

    public RedirectService(UrlMappingRepository repository,
                           RedisTemplate<String, String> redisTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
    }

    public String resolveShortCode(String shortCode) {

        String redisKey = "url:" + shortCode;

        /* 1️⃣ Try Redis first */
        String cachedUrl = redisTemplate.opsForValue().get(redisKey);

        if (cachedUrl != null) {
            // Cache hit → fastest path
            return cachedUrl;
        }

        /* 2️⃣ Cache miss → hit database */
        Optional<UrlMapping> mappingOpt =
                repository.findByShortCode(shortCode);

        if (mappingOpt.isEmpty()) {
            return null;
        }

        String longUrl = mappingOpt.get().getLongUrl();

        /* 3️⃣ Store in Redis for next time */
        redisTemplate.opsForValue()
                .set(redisKey, longUrl, CACHE_TTL);

        return longUrl;
    }
}
