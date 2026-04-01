package com.test.firstproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class Controller {

    Student s = null;
    @GetMapping("/save-student")
    public String setname(){

        s = new Student();
        s.setName("Kevin");
        s.setAge(20);
        s.setAddress("Nikol");
        s.setPhoneNumber(762190882);
        s.setPincode(382350);
        s.setStatus("Success");

        return "Saved successfully";
    }

    @GetMapping("/get-student")
    public Student getName(){
        if(s==null){
            s = new Student(null,0,0,null,0,"Student not found");
        }
        return s;
    }
}
