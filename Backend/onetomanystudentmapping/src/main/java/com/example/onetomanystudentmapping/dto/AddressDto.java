package com.example.onetomanystudentmapping.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String street;
    private String city;
    private Long pincode;
    private StudentDto student;
}
