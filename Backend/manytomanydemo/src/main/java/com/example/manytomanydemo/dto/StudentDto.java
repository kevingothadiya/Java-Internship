package com.example.manytomanydemo.dto;


import lombok.Data;

import java.util.List;

@Data
public class StudentDto {

    private Long id;
    private String name;
    private String email;
    private String department;
    private List<CourceDto> cources;

}
