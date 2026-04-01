package com.example.onetomanystudentmapping.controller;

import com.example.onetomanystudentmapping.dto.StudentDto;
import com.example.onetomanystudentmapping.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/save")
    public String saveStdData(@RequestBody StudentDto studentDto){
        return studentService.saveStdData(studentDto);
    }

    @GetMapping("/get-all")
    public List<StudentDto> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/get-by-id/{id}")
    public StudentDto getStdById(@PathVariable Long id){
        return studentService.getStdById(id);
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@RequestBody StudentDto studentDto,@PathVariable Long id){
        return studentService.updateStudent(studentDto,id);
    }

    @DeleteMapping("/delete-by-id")
    public String removeStdById(@RequestParam Long id){
        return studentService.removeStdById(id);
    }

    @DeleteMapping("/delete")
    public String removeAllStd(){
        return studentService.removeAllStd();
    }
}
