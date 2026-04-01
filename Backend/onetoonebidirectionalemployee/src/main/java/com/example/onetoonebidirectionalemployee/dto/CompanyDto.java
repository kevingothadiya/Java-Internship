package com.example.onetoonebidirectionalemployee.dto;

import lombok.Data;

@Data
public class CompanyDto {

    private Long id;
    private String comName;
    private String comAddress;
    private EmployeeDto employee;
}
