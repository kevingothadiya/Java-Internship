package com.example.globalexceptiondemo.controller;

import com.example.globalexceptiondemo.proxy.StudentProxy;
import com.example.globalexceptiondemo.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/save")
    public ResponseEntity<String> SaveAll(@RequestBody StudentProxy studentProxy){
        String s = studentService.SaveAll(studentProxy);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentProxy>> getAllStudent(){
        List<StudentProxy> allStudent = studentService.getAllStudent();
        return new ResponseEntity<>(allStudent,HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<StudentProxy>> getStudentByName(@PathVariable String name){
        List<StudentProxy> studentByName = studentService.getStudentByName(name);
        return new ResponseEntity<>(studentByName,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody StudentProxy studentProxy,@PathVariable Long id){
        String s = studentService.updateStudent(studentProxy, id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @DeleteMapping("/remove-by-id/{id}")
    public ResponseEntity<String> removeById(@PathVariable Long id){
        String s = studentService.removeById(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @DeleteMapping("/remove-all")
    public ResponseEntity<String> removeAll() {
        String s = studentService.removeAll();
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

}
