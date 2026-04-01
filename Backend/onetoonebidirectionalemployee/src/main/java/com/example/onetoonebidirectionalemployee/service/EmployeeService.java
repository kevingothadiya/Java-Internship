package com.example.onetoonebidirectionalemployee.service;

import com.example.onetoonebidirectionalemployee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    String saveEmpData(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmpData();

    EmployeeDto findEmpByEmail(String email);

    String updateEmp(EmployeeDto employeeDto,Long id);

    String removeById(Long id);

    String removeAll();

}
