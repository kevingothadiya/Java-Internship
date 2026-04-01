package com.example.customquerymethods.controller;

import com.example.customquerymethods.dto.StudentDto;
import com.example.customquerymethods.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl service;

    @GetMapping("/add")
    public ResponseEntity<String> generateRendomData(){
        String s = service.generateRendomData();
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentDto>> getAll(){
        List<StudentDto> all = service.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/find-by-firstname/{name}")
    public ResponseEntity<List<StudentDto>> findByName(@PathVariable String name){
        List<StudentDto> studentByFirstName = service.findStudentByFirstName(name);
        return new ResponseEntity<>(studentByFirstName,HttpStatus.OK);
    }

    @GetMapping("/find-by-firstname-and-lastname/{firstname}/{lastname}")
    public ResponseEntity<List<StudentDto>> findStudentByFirstNameAndLastName(@PathVariable String firstname,@PathVariable String lastname){
        List<StudentDto> studentByFirstNameAndLastName = service.findStudentByFirstNameAndLastName(firstname, lastname);
        return new ResponseEntity<>(studentByFirstNameAndLastName,HttpStatus.OK);
    }

    @GetMapping("/find-by-firstname-or-lastname/{firstname}/{lastname}")
    public ResponseEntity<List<StudentDto>> findStudentByFirstNameOrLastName(@PathVariable String firstname,@PathVariable String lastname){
        List<StudentDto> studentByFirstNameOrLastName = service.findStudentByFirstNameOrLastName(firstname, lastname);
        return new ResponseEntity<>(studentByFirstNameOrLastName,HttpStatus.OK);
    }

    @GetMapping("/find-student-by-firstname-starting-with/{firstname}")
    public ResponseEntity<List<StudentDto>> findStudentByFirstnameStartingWith(@PathVariable String firstname){
        List<StudentDto> studentByFirstnameStartingWith = service.findStudentByFirstnameStartingWith(firstname);
        return new ResponseEntity<>(studentByFirstnameStartingWith,HttpStatus.OK);
    }

    @GetMapping("/find-student-by-firstname-like/{firstname}")
    public ResponseEntity<List<StudentDto>> findStudentByFirstnameLike(@PathVariable String firstname){
        List<StudentDto> studentByFirstnameLike = service.findStudentByFirstnameLike(firstname);
        return new ResponseEntity<>(studentByFirstnameLike,HttpStatus.OK);
    }

    @GetMapping("/find-student-by-firstname-containing/{firstname}")
    public ResponseEntity<List<StudentDto>> findStudentByFirstnameContaining(@PathVariable String firstname){
        List<StudentDto> studentByFirstnameContaining = service.findStudentByFirstnameContaining(firstname);
        return new ResponseEntity<>(studentByFirstnameContaining,HttpStatus.OK);
    }

    @GetMapping("/find-student-by-age-less-than-equal/{age}")
    public ResponseEntity<List<StudentDto>> findStudentByAgeLessThanEqual(@PathVariable Integer age){
        List<StudentDto> studentByAgeLessThanEqual = service.findStudentByAgeLessThanEqual(age);
        return new ResponseEntity<>(studentByAgeLessThanEqual,HttpStatus.OK);
    }

    @GetMapping("/find-student-by-age-greater-than/{age}")
    public ResponseEntity<List<StudentDto>> findStudentByAgeGreaterThan(@PathVariable Integer age){
        List<StudentDto> studentByAgeGreaterThan = service.findStudentByAgeGreaterThan(age);
        return new ResponseEntity<>(studentByAgeGreaterThan,HttpStatus.OK);
    }
}
