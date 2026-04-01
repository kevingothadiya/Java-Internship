package com.example.securitydemo.controller;

import com.example.securitydemo.dto.StudentDto;
import com.example.securitydemo.service.implementation.StudentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl service;

    @GetMapping("/verify")
    public String printData(){
        return "Security is working";
    }

    @PostMapping("/getRemoteAddr")
    public String getUserIpAddress(HttpServletRequest request){
        return request.getRemoteAddr();
    }

    @GetMapping("/session")
    public String getSessionID(HttpServletRequest request){
        return request.getSession().getId();
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @PostMapping("/add-data")
    public String addStudent(@RequestBody StudentDto studentDto){
        return service.addStudent(studentDto);
    }
}
