package com.example.manytomanydemo.controller;

import com.example.manytomanydemo.dto.StudentDto;
import com.example.manytomanydemo.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/save")
    public String saveAll(@RequestBody StudentDto studentDto){
        return studentService.saveAll(studentDto);
    }

    @GetMapping("/get-all")
    public List<StudentDto> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/get-by-department/{department}")
    public List<StudentDto> getByDepartment(@PathVariable String department){
        return studentService.getByDepartment(department);
    }

    @GetMapping("/get-by-name/{name}")
    public List<StudentDto> getByName(@PathVariable String name){
        return studentService.getByName(name);
    }
}
