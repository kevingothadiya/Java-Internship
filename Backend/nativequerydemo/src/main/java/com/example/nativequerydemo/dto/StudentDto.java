package com.example.nativequerydemo.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String address;
}
