package com.example.onttomanyfullproject.controller;

import com.example.onttomanyfullproject.dto.StudentDto;
import com.example.onttomanyfullproject.model.AuthRequest;
import com.example.onttomanyfullproject.model.AuthResponse;
import com.example.onttomanyfullproject.service.implementation.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/add")
    public ResponseEntity<String> addStudentData(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return new ResponseEntity<>(studentService.generateToken(request),HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<StudentDto>> getAllStudentData(){
        return new ResponseEntity<>(studentService.getAllStudent(),HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<StudentDto> getStudentDataById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateByID(@PathVariable Long id,@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateByID(id, studentDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{emailId}")
    public ResponseEntity<String> removeStudentByEmailId(@PathVariable String emailId){
        return new ResponseEntity<>(studentService.removeStudentByEmailId(emailId),HttpStatus.OK);
    }
}
