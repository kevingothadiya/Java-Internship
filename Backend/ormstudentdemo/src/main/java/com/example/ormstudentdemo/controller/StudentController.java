package com.example.ormstudentdemo.controller;

import com.example.ormstudentdemo.entity.StudentEntity;
import com.example.ormstudentdemo.service.implementation.StudentImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentImpl stdimp;

    @PostMapping("/save-data")
    public String saveStdData(@RequestBody StudentEntity std){
        return stdimp.saveData(std);
    }

    @GetMapping("/get-data")
    public List<StudentEntity> getStdData(){
        return stdimp.gatData();
    }

    @GetMapping("/get-data-by-id/{id}")
    public StudentEntity getStdDataById(@PathVariable Long id){
        return stdimp.getDataById(id);
    }

    @PostMapping("/update/{id}")
    public String updateStdData(@RequestBody StudentEntity std,@PathVariable Long id){
        return stdimp.updateData(std,id);
    }

    @GetMapping("/remove-by-id/{id}")
    public  String removeStDataById(@PathVariable Long id){
        return stdimp.removeDataById(id);
    }

    @GetMapping("/remove")
    public String removeAllStdData(){
        return stdimp.removeData();
    }
}
