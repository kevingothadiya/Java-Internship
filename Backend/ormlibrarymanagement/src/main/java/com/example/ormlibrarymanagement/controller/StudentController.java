package com.example.ormlibrarymanagement.controller;

import com.example.ormlibrarymanagement.entity.Student;
import com.example.ormlibrarymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/get")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable Long id){
        return studentService.deleteStudentById(id);
    }

    @GetMapping("/get/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,@RequestBody Student student){
        return studentService.updateStudent(id, student);
    }
}
