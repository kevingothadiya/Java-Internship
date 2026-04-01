package com.example.onetoonebidirectional.controller;

import com.example.onetoonebidirectional.dto.StudentDto;
import com.example.onetoonebidirectional.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/save")
    public String saveStudentData(@RequestBody StudentDto studentDto){
        return studentService.saveData(studentDto);
    }

    @GetMapping("/get-all")
    public List<StudentDto> getAllStudentData(){
        return studentService.getAllData();
    }

    @GetMapping("/get/{id}")
    public StudentDto getDataById(@PathVariable Long id){
        return studentService.getDataById(id);
    }
}
