package com.example.hospitalservice.domain;

import lombok.Data;

@Data
public class Users {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phoneNum;
    private String status;
}
