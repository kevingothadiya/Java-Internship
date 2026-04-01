package com.example.manytomanydemo.service;

import com.example.manytomanydemo.dto.StudentDto;
import com.example.manytomanydemo.entity.Student;

import java.util.List;

public interface StudentService {

    String saveAll(StudentDto studentDto);

    List<StudentDto> getAllStudent();

    List<StudentDto> getByDepartment(String department);

    List<StudentDto> getByName(String name);

}
