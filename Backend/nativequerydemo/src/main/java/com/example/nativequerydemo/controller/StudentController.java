package com.example.nativequerydemo.controller;

import com.example.nativequerydemo.dto.StudentDto;
import com.example.nativequerydemo.projection.FirstNameLastNameAddress;
import com.example.nativequerydemo.service.implementaion.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/add")
    public String addFakerData(){
        return studentService.addFakerData();
    }

    @GetMapping("/get-all")
    public List<StudentDto> getAllStudentData(){
        return studentService.getAllStudentData();
    }

    @GetMapping("/find-student-by-email/{email}")
    public StudentDto getStudentByEmailID(@PathVariable String email){
        return studentService.getStudentByEmailID(email);
    }

    @GetMapping("/find-address-name-by-email/{email}")
    public Object[] getStudentAddressAndFirstnameByEmailID(@PathVariable String email){
        return studentService.getStudentAddressAndFirstnameByEmailID(email);
    }

    @GetMapping("/find-student-first-name-last-name-address-by-id/{id}")
    public FirstNameLastNameAddress getStudentFirstNameLastNameAddressByID(@PathVariable Long id){
        return studentService.getStudentFirstNameLastNameAddressByID(id);
    }

    @PostMapping("/update-by-id/{id}")
    public void updateStudentById(@PathVariable Long id,@RequestBody StudentDto studentDto){
        studentService.updateStudentById(id,studentDto);
    }

    @PostMapping("/insert")
    public void insertStudentData(@RequestBody StudentDto studentDto){
        studentService.insertStudentData(studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentByID(@PathVariable Long id){
        studentService.deleteStudentByID(id);
    }
}
