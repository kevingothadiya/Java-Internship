package com.example.ormlibrarymanagement.service;

import com.example.ormlibrarymanagement.entity.Student;

import java.util.List;

public interface StudentService {
    String addStudent(Student student);

    List<Student> getAllStudent();

    String deleteStudentById(Long id);

    Student getStudentById(Long id);

    String updateStudent(Long id,Student student);
}
