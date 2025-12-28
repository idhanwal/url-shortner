package com.idhanwal.urlshortener.controller;

import com.idhanwal.urlshortener.dto.ShortenRequest;
import com.idhanwal.urlshortener.dto.ShortenResponse;
import com.idhanwal.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShortenController {

    private final UrlService urlService;

    public ShortenController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shorten(
            @RequestBody ShortenRequest request) {

        ShortenResponse response =
                urlService.shortenUrl(request.getLongUrl());

        return ResponseEntity.ok(response);
    }
}