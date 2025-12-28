package com.idhanwal.urlshortener.dto;


public class ShortenResponse {

    private String shortCode;
    private String shortUrl;
    private String longUrl;

    public ShortenResponse(String shortCode, String shortUrl, String longUrl) {
        this.shortCode = shortCode;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }
}
