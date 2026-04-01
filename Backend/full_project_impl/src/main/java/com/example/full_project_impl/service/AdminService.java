package com.example.full_project_impl.service;

import com.example.full_project_impl.dto.EmployeeDto;

import java.util.List;

public interface AdminService {

    List<EmployeeDto> getAllEmployee();

    String updateEmployee(Long id, EmployeeDto employeeDto);

    String deleteEmployeeById(Long id);

}
