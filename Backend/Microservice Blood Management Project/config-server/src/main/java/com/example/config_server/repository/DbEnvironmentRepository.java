package com.example.config_server.repository;


import com.example.config_server.entity.ConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DbEnvironmentRepository implements EnvironmentRepository {

    @Autowired
    private ConfigRepository repo;

    @Override
    public Environment findOne(String application, String profile, String label) {

        if (profile == null) {
            profile = "default";
        }

        Map<String, Object> props = new HashMap<>();

        // COMMON config
        List<ConfigProperty> common =
                repo.findByApplicationAndProfile("application", profile);

        for (ConfigProperty c : common) {
            props.put(c.getPropKey(), c.getPropValue());
        }

        // SERVICE config
        List<ConfigProperty> appProps =
                repo.findByApplicationAndProfile(application, profile);

        for (ConfigProperty c : appProps) {
            props.put(c.getPropKey(), c.getPropValue());
        }

        // ✅ Correct constructor for your version
        Environment environment =
                new Environment(application, profile, null, null);

        environment.add(new PropertySource("db", props));

        return environment;
    }
}