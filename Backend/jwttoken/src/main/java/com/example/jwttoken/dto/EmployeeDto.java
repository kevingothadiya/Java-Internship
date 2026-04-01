package com.example.jwttoken.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String userName;
    private String password;
    private String role;
    private String address;
    private String email;
}
