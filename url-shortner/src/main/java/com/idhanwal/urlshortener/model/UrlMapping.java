package com.idhanwal.urlshortener.model;


import jakarta.persistence.*;

@Entity
@Table(name = "url_mappings")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_code", nullable = false, unique = true)
    private String shortCode;

    @Column(name = "long_url", nullable = false)
    private String longUrl;

    /* ===== GETTERS ===== */

    public Long getId() {
        return id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    /* ===== SETTERS ===== */

    public void setId(Long id) {
        this.id = id;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}