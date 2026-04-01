package com.example.onetomanymapping.controller;

import com.example.onetomanymapping.domain.Student;
import com.example.onetomanymapping.service.StudentService;
import com.example.onetomanymapping.service.implementation.StudenetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudenetServiceImpl student;

    @PostMapping("/save-data")
    public String saveStdData(@RequestBody Student std){
        return student.saveData(std);
    }

    @GetMapping("/get-data")
    public List<Student> getAllStdData(){
        return student.getAllData();
    }

    @GetMapping("/get-data-email/{email}")
    public Student getStdDataByEmailId(@PathVariable String email){
        return student.getDataByEmailId(email);
    }

    @PostMapping("/update/{id}")
    public String updateStdData(@RequestBody Student std,@PathVariable Long id){
        return student.updateData(std, id);
    }

    @GetMapping("/remove-by-id/{id}")
    public String removeStdDataById(@PathVariable Long id){
        return student.removeDataById(id);
    }

    @GetMapping("/remove")
    public String removeAllStdData(){
        return student.removeAll();
    }
}
