package com.example.jpastudentdemo.controller;

import com.example.jpastudentdemo.domain.StudentDomain;
import com.example.jpastudentdemo.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl stdimp;

    @PostMapping("/save-data")
    public String saveStdData(@RequestBody StudentDomain std){
        return stdimp.saveData(std);
    }

    @GetMapping("/get-all-data")
    public List<StudentDomain> getAllData(){
        return stdimp.getAllStudentData();
    }

    @GetMapping("/get-data-by-email/{email}")
    public StudentDomain getByEmailId(@PathVariable String email){
        return stdimp.getByEmail(email);
    }

    @GetMapping("/remove-by-id/{id}")
    public String removeById(@PathVariable Long id){
        return stdimp.removeById(id);
    }

    @PostMapping("/update/{id}")
    public String updateData(@RequestBody StudentDomain std,@PathVariable Long id){
        return stdimp.update(std,id);
    }

    @GetMapping("/remove-all")
    public String removeAllData(){
        return stdimp.removeAll();
    }
}
