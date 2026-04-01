package com.example.jwttoken.controller;

import com.example.jwttoken.dto.EmployeeDto;
import com.example.jwttoken.model.AuthRequest;
import com.example.jwttoken.model.AuthResponse;
import com.example.jwttoken.service.implementation.EmployeeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl service;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        AuthResponse authResponse = service.authenticateStudent(request);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployeeData(@RequestBody EmployeeDto employeeDto){
        String s = service.addEmployee(employeeDto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/get")     //USER
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        return new ResponseEntity<>(service.getAllEmployee(),HttpStatus.OK);
    }

    @PostMapping("/user-details") //USER
    public String printUserDetails(){
        return "This is User Details";
    }

    @GetMapping("/csrf")    //ADMIN
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @PostMapping("/session")  //ADMIN
    public String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }
}
