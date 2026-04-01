package com.example.securitydemo.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String userName;
    private String password;
    private String address;
    private String email;
}
