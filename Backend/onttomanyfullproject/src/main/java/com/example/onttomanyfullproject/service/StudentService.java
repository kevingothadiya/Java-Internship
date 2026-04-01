package com.example.onttomanyfullproject.service;

import com.example.onttomanyfullproject.dto.StudentDto;
import com.example.onttomanyfullproject.model.AuthRequest;
import com.example.onttomanyfullproject.model.AuthResponse;

import java.util.List;

public interface StudentService {

    String addStudent(StudentDto studentDto);

    AuthResponse generateToken(AuthRequest request);

    List<StudentDto> getAllStudent();

    StudentDto getStudentById(Long id);

    String updateByID(Long id,StudentDto studentDto);

    String removeStudentByEmailId(String emailId);
}
