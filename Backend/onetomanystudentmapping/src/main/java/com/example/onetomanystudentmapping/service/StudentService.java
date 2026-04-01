package com.example.onetomanystudentmapping.service;

import com.example.onetomanystudentmapping.dto.StudentDto;

import java.util.List;

public interface StudentService {

    String saveStdData(StudentDto studentDto);

    List<StudentDto> getAllStudent();

    StudentDto getStdById(Long id);

    String updateStudent(StudentDto studentDto,Long id);

    String removeStdById(Long id);

    String removeAllStd();
}
