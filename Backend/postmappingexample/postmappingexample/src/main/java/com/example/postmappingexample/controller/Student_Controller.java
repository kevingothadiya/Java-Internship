package com.example.postmappingexample.controller;

import com.example.postmappingexample.student.Student;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("/student")
public class Student_Controller {

    Set<Student> stdData = new HashSet<>();
    @GetMapping(value = "/set-student/{id}/{name}/{address}")
    public String setStudentData(@PathVariable int id,@PathVariable String name,@PathVariable String address){
        Optional<Student> first = stdData.stream().filter(x -> x.getId() == id).findFirst();
        if(first.isEmpty()) {
            Student s = new Student();
            s.setId(id);
            s.setName(name);
            s.setAddress(address);
            stdData.add(s);
            return "Data Successfully added";
        }
        else {
            return "Data has Already Available with id " + id;
        }
    }

    @GetMapping("/get-student-data")
    public Set<Student> getStudentData(){
        return stdData;
    }

    @GetMapping("/get-studentbyid/{id}")
    public Student getStudentById(@PathVariable Integer id){
        Student std = stdData.stream().filter(a -> a.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("No Data Found"));
        return std;
    }

    @GetMapping("/remove-data")
    public String removeStudentData(){
        if(stdData.isEmpty()||stdData==null){
            return "No Data Available";
        }
        else {
            stdData.clear();
            return "All Data removed Successfully";
        }
    }

    @GetMapping("/remove-databyid/{id}")
    public String removeDataById(@PathVariable Integer id){
        boolean remove = stdData.removeIf(s -> s.getId() == id);
        if(remove){
            return "Data With ID " + id + " are successfully removed";
        }
        else {
            return "No Data with Id " + id;
        }
    }

    @GetMapping("update-data")
    public String updateData(@RequestParam (name = "id")Integer stdid,@RequestParam String name,@RequestParam(required = false) String address){
        Optional<Student> optionalStudent = stdData.stream().filter(x -> x.getId() == stdid).findFirst();
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setName(name);
            student.setAddress(address);
            stdData.add(student);

            return "Data Successfully updated";
        }
        else {
            return "No Data Available with Id " +stdid;
        }
    }
}
