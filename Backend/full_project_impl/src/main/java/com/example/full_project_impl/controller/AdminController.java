package com.example.full_project_impl.controller;

import com.example.full_project_impl.dto.EmployeeDto;
import com.example.full_project_impl.model.AuthRequest;
import com.example.full_project_impl.model.AuthResponse;
import com.example.full_project_impl.service.implementation.AdminServiceImpl;
import com.example.full_project_impl.service.implementation.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/get")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        return new ResponseEntity<>(adminServiceImpl.getAllEmployee(), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(adminServiceImpl.updateEmployee(id, employeeDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){
        return new ResponseEntity<>(adminServiceImpl.deleteEmployeeById(id),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest request){
        return new ResponseEntity<>(employeeService.generateToken(request),HttpStatus.OK);
    }
}
