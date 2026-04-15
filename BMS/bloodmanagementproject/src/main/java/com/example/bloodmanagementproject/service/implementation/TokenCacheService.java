package com.example.bloodmanagementproject.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenCacheService {

    private static final String PREFIX = "auth:token:";
    private static final Duration TOKEN_TTL = Duration.ofMinutes(20);

    @Autowired
    private StringRedisTemplate redisTemplate;

    // Store token in Redis with 20-min TTL
    public void saveToken(String email, String token) {
        redisTemplate.opsForValue().set(PREFIX + email, token, TOKEN_TTL);
    }

    // Get cached token for email (returns null if not found or expired)
    public String getToken(String email) {
        return redisTemplate.opsForValue().get(PREFIX + email);
    }

    // Remove token (on logout or password reset)
    public void removeToken(String email) {
        redisTemplate.delete(PREFIX + email);
    }

    // Check if a valid non-expired token exists in Redis
    public boolean hasValidToken(String email) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(PREFIX + email));
    }
}
