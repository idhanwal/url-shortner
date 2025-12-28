package com.idhanwal.urlshortener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot application.
 * Spring scans this package (com.example.urlshortener) and all subpackages
 * for @Controller, @Service, @Repository, @Entity, @Configuration, etc.
 */
@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}