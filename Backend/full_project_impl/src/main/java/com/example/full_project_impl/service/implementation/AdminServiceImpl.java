package com.example.full_project_impl.service.implementation;

import com.example.full_project_impl.custom.exception.NoEmployeeFoundException;
import com.example.full_project_impl.dto.EmployeeDto;
import com.example.full_project_impl.entity.Employee;
import com.example.full_project_impl.helper.MapperHelper;
import com.example.full_project_impl.repository.EmployeeRepo;
import com.example.full_project_impl.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<EmployeeDto> getAllEmployee() {
        if(employeeRepo.findAll().isEmpty()){
            throw new RuntimeException("No Employee Available");
        }
        return helper.getListEmployeeDto(employeeRepo.findAll());
    }

    @Override
    public String updateEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> byId = employeeRepo.findById(id);
        if(byId.isPresent()){
            employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
            employeeRepo.save(helper.getEmployeeEntity(employeeDto));
            return "Update Successfully";
        }
        else {
            throw new NoEmployeeFoundException("No Employee Found With Id : " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String deleteEmployeeById(Long id) {
        Optional<Employee> byId = employeeRepo.findById(id);
        if(byId.isPresent()){
            employeeRepo.deleteById(id);
            return "Delete Successfully of Employee Data With ID : " + id;
        }
        else {
            throw new NoEmployeeFoundException("No Employee Found With ID : " + id,HttpStatus.NOT_FOUND.value());
        }
    }
}
