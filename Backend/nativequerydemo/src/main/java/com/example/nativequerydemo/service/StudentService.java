package com.example.nativequerydemo.service;

import com.example.nativequerydemo.dto.StudentDto;
import com.example.nativequerydemo.entity.Student;
import com.example.nativequerydemo.projection.FirstNameLastNameAddress;

import java.util.List;
import java.util.Objects;

public interface StudentService {

    String addFakerData();

    List<StudentDto> getAllStudentData();

    StudentDto getStudentByEmailID(String email);

    Object[] getStudentAddressAndFirstnameByEmailID(String email);

    FirstNameLastNameAddress getStudentFirstNameLastNameAddressByID(Long id);

    void updateStudentById(Long id,StudentDto studentDto);

    void insertStudentData(StudentDto studentDto);

    void deleteStudentByID(Long id);
}
