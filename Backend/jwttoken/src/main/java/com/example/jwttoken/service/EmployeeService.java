package com.example.jwttoken.service;

import com.example.jwttoken.dto.EmployeeDto;
import com.example.jwttoken.model.AuthRequest;
import com.example.jwttoken.model.AuthResponse;

import java.util.List;

public interface EmployeeService {

    String addEmployee(EmployeeDto employeeDto);

    AuthResponse authenticateStudent(AuthRequest request);

    List<EmployeeDto> getAllEmployee();
}
