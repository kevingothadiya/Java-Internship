package com.example.postmapping.controller;

import com.example.postmapping.employee.Employee;
import com.example.postmapping.service.Employee_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class Employee_Controller {
    @Autowired
    private Employee_Service empService;

    @PostMapping("/set-data")
    public String setData(@RequestBody  Employee e){
        return empService.setData(e);
    }

    @GetMapping("/get-data")
    public List<Employee> getData(){
        return empService.getData();
    }

    @GetMapping("/get-data-byid/{id}")
    public Employee getDataById(@PathVariable Long id){
        return empService.getDataById(id);
    }

    @GetMapping("/remove-data")
    public String removeData(){
        return empService.removeData();
    }

    @GetMapping("/remove-data-byid/{id}")
    public String removeDataById(@PathVariable Long id){
        return empService.removeDataByid(id);
    }

    @GetMapping("/update-data/{id}")
    public String updateData(@PathVariable Long id,@RequestBody Employee e){
        return empService.updateData(id,e);
    }
}
