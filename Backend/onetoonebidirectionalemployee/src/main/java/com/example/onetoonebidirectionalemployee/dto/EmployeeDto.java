package com.example.onetoonebidirectionalemployee.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;
    private String name;
    private String address;
    private String email;
    private CompanyDto company;
}
