package com.example.config_server.repository;

import com.example.config_server.entity.ConfigProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepository extends JpaRepository<ConfigProperty,Long> {
    List<ConfigProperty> findByApplicationAndProfile(String application, String profile);

    List<ConfigProperty> findByApplication(String application);
}
