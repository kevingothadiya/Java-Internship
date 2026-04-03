package com.example.bloodmanagementproject.model;

import lombok.Data;

@Data
public class AuthResponse {

    private String token;
    private String email;
    private String role;
}
