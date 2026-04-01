package com.example.onetoonebidirectional.service;

import com.example.onetoonebidirectional.dto.StudentDto;

import java.util.List;

public interface StudentService {

    String saveData(StudentDto studentDto);

    List<StudentDto> getAllData();

    StudentDto getDataById(Long id);
}
