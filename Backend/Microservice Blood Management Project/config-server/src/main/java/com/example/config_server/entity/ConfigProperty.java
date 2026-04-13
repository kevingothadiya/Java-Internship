package com.example.config_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConfigProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String application;
    private String profile;
    private String propKey;

    public String getPropValue() {
        return propValue;
    }

    public String getPropKey() {
        return propKey;
    }

    public String getProfile() {
        return profile;
    }

    public String getApplication() {
        return application;
    }

    public Long getId() {
        return id;
    }

    private String propValue;
}
