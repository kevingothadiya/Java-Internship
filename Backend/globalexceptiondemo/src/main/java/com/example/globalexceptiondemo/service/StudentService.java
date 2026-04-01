package com.example.globalexceptiondemo.service;

import com.example.globalexceptiondemo.proxy.StudentProxy;

import java.util.List;

public interface StudentService {

    String SaveAll(StudentProxy studentProxy);

    List<StudentProxy> getAllStudent();

    List<StudentProxy> getStudentByName(String name);

    String updateStudent(StudentProxy studentProxy,Long id);

    String removeById(Long id);

    String removeAll();
}
