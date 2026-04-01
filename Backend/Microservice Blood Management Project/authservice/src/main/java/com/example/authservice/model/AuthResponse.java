package com.example.authservice.model;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String email;
}
