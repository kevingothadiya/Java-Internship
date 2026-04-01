package com.example.jwttoken.helper;

import com.example.jwttoken.dto.EmployeeDto;
import com.example.jwttoken.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class MapperHelper {

    @Autowired
    private ObjectMapper mapper;

    public Employee getEmployeeEntity(EmployeeDto employeeDto){
        return mapper.convertValue(employeeDto, Employee.class);
    }

    public EmployeeDto getEmployeeDto(Employee employee){
        return mapper.convertValue(employee, EmployeeDto.class);
    }

    public List<Employee> getListEmployeeEntity(List<EmployeeDto> employeeDtos){
        return employeeDtos.stream().map(this::getEmployeeEntity).toList();
    }

    public List<EmployeeDto> getListEmployeeDto(List<Employee> employees){
        return employees.stream().map(this::getEmployeeDto).toList();
    }
}
