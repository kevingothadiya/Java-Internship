package com.example.jwttoken.model;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String userName;
}
