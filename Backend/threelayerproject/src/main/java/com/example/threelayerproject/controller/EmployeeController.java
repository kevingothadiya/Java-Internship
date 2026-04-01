package com.example.threelayerproject.controller;

import com.example.threelayerproject.model.Employee;
import com.example.threelayerproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService empdata;

    @PostMapping("save-data")
    public String saveEmpData(@RequestBody Employee emp){
        return empdata.saveData(emp);
    }
    @GetMapping("/get-all-data")
    public List<Employee> getAllEmpData(){
        return empdata.getAllData();
    }

    @GetMapping("/get-data-by-id/{id}")
    public  Employee getEmpById(@PathVariable Long id){
        return empdata.getById(id);
    }

    @PostMapping("/update-data/{id}")
    public String updateEmpData(@RequestBody Employee e,@PathVariable Long id){
        return empdata.updateData(e,id);
    }

    @GetMapping("/remove-data-by-id/{id}")
    public String removeEmpDataWithId(@PathVariable Long id){
        return empdata.removeById(id);
    }

    @GetMapping("/remove-all-data")
    public String removeEmpData(){
        return empdata.removeAllData();
    }
}
