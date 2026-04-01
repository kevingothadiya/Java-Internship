package com.example.onetoonebidirectionalemployee.controller;

import com.example.onetoonebidirectionalemployee.dto.EmployeeDto;
import com.example.onetoonebidirectionalemployee.service.implementation.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/save")
    public ResponseEntity<String> saveEmpData(@RequestBody EmployeeDto employeeDto){
        String s = employeeService.saveEmpData(employeeDto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeDto>> getAllEmpData(){
        List<EmployeeDto> allEmpData = employeeService.getAllEmpData();
        return new ResponseEntity<>(allEmpData, HttpStatus.OK);
    }

    @GetMapping("/find-by-email")
    public ResponseEntity<EmployeeDto> findEmpByEmail(@RequestParam String email){
        EmployeeDto empByEmail = employeeService.findEmpByEmail(email);
        return new ResponseEntity<>(empByEmail, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateEmp(@RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        String s = employeeService.updateEmp(employeeDto, id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/remove-by-id/{id}")
    public ResponseEntity<String> removeById(@PathVariable Long id){
        String s = employeeService.removeById(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/remove")
    public ResponseEntity<String> removeAll(){
        String s = employeeService.removeAll();
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
