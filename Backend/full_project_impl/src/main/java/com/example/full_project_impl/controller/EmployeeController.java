package com.example.full_project_impl.controller;

import com.example.full_project_impl.dto.EmployeeDto;
import com.example.full_project_impl.model.AuthRequest;
import com.example.full_project_impl.model.AuthResponse;
import com.example.full_project_impl.service.implementation.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-by-name/{userName}")
    public ResponseEntity<EmployeeDto> getEmployeeByName(@PathVariable String userName){
        return new ResponseEntity<>(employeeService.getEmployeeByName(userName),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest request){
        return new ResponseEntity<>(employeeService.generateToken(request),HttpStatus.OK);
    }
}
