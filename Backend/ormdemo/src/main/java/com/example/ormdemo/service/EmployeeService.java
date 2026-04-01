package com.example.ormdemo.service;

import com.example.ormdemo.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    String saveData(EmployeeEntity emp);

    List<EmployeeEntity> getAllData();

    EmployeeEntity getById(Long id);

    String updateData(EmployeeEntity emp,Long id);

    String removeById(Long id);

    String removeAllData();
}
