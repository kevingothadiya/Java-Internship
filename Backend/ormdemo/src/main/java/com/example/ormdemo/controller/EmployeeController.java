package com.example.ormdemo.controller;

import com.example.ormdemo.entity.EmployeeEntity;
import com.example.ormdemo.service.implementatiom.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employee;

    @PostMapping("/save-data")
    public String saveEmpData(@RequestBody EmployeeEntity emp){
        return employee.saveData(emp);
    }

    @GetMapping("/get-all-data")
    public List<EmployeeEntity> getEmpData(){
        return employee.getAllData();
    }

    @GetMapping("/get-by-id/{id}")
    public EmployeeEntity getEmpById(@PathVariable Long id){
        return employee.getById(id);
    }

    @PostMapping("/update/{id}")
    public String updateEmpData(@RequestBody EmployeeEntity emp,@PathVariable Long id){
        return employee.updateData(emp,id);
    }

    @GetMapping("/remove-by-id/{id}")
    public String removeEmpDataById(@PathVariable long id){
        return employee.removeById(id);
    }

    @GetMapping("/remove-all-data")
    public String removeAllEmpData(){
        return employee.removeAllData();
    }
}
