package com.example.jpastudentdemo.service;

import com.example.jpastudentdemo.domain.StudentDomain;

import java.util.List;

public interface StudentService {

     String saveData(StudentDomain std);

     List<StudentDomain> getAllStudentData();

     StudentDomain getByEmail(String email);

     String removeById(Long id);

     String update(StudentDomain std,Long id);

     String removeAll();
}
