package com.example.ormstudentdemo.service;

import com.example.ormstudentdemo.entity.StudentEntity;

import java.util.List;

public interface StudentInterface {

    String saveData(StudentEntity std);

    List<StudentEntity> gatData();

    StudentEntity getDataById(Long id);

    String updateData(StudentEntity std,Long id);

    String removeDataById(Long id);

    String removeData();
}
