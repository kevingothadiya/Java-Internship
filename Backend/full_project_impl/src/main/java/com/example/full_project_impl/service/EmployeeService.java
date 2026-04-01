package com.example.full_project_impl.service;

import com.example.full_project_impl.dto.EmployeeDto;
import com.example.full_project_impl.model.AuthRequest;
import com.example.full_project_impl.model.AuthResponse;

import java.util.List;

public interface EmployeeService {

    String saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeByName(String userName);

    AuthResponse generateToken(AuthRequest request);
}
