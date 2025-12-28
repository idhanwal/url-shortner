package com.idhanwal.urlshortener.service;

import com.idhanwal.urlshortener.dto.ShortenResponse;
import com.idhanwal.urlshortener.model.UrlMapping;
import com.idhanwal.urlshortener.repository.UrlMappingRepository;
import com.idhanwal.urlshortener.util.Base62;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private static final String BASE_URL = "http://localhost:8080";

    private final UrlMappingRepository repository;
    private final RedisTemplate<String, String> redisTemplate;

    public UrlService(UrlMappingRepository repository,
                      RedisTemplate<String, String> redisTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
    }

    public ShortenResponse shortenUrl(String longUrl) {

        if (longUrl == null || longUrl.isBlank()) {
            throw new IllegalArgumentException("Long URL cannot be empty");
        }

        String shortCode = Base62.encode(System.currentTimeMillis());

        UrlMapping mapping = new UrlMapping();
        mapping.setShortCode(shortCode);
        mapping.setLongUrl(longUrl);

        repository.save(mapping);

        // Invalidate cache just in case
        redisTemplate.delete("url:" + shortCode);

        String shortUrl = BASE_URL + "/" + shortCode;

        return new ShortenResponse(shortCode, shortUrl, longUrl);
    }
}