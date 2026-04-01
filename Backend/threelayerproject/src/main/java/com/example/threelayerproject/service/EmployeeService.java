package com.example.threelayerproject.service;

import com.example.threelayerproject.model.Employee;

import java.util.List;

public interface EmployeeService {

    String saveData(Employee emp);

    List<Employee> getAllData();

    Employee getById(Long id);

    String updateData(Employee emp,Long id);

    String removeById(Long id);

    String removeAllData();
}
