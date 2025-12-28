package com.idhanwal.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;

public class ShortenRequest {

    @NotBlank
    @URL
    private String longUrl;

    private Instant expiresAt;

    public String getLongUrl() { return longUrl; }
    public Instant getExpiresAt() { return expiresAt; }
}