package com.example.onetomanystudentmapping.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDto {

    private Long id;
    private String name;
    private String clgname;
    private String branch;
    private List<AddressDto> address = new ArrayList<>();
}
