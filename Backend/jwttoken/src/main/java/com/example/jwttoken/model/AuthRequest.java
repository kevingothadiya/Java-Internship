package com.example.jwttoken.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String password;
}
