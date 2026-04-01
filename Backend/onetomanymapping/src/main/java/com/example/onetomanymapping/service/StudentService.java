package com.example.onetomanymapping.service;

import com.example.onetomanymapping.domain.Student;

import java.util.List;

public interface StudentService {

    public String saveData(Student std);

    List<Student> getAllData();

    Student getDataByEmailId(String email);

    String updateData(Student std,Long id);

    String removeDataById(Long id);

    String removeAll();
}
