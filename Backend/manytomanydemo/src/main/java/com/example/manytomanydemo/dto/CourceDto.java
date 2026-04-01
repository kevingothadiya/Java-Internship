package com.example.manytomanydemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourceDto {

    private Long id;
    private String title;
    private String modules;
    private Double fees;
    private List<StudentDto> students;

}
