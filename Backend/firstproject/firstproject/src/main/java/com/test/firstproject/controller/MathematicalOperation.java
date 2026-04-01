package com.test.firstproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathematicalOperation {
    int a = 20;
    int b = 0;

    @GetMapping("/get-add")
    public int addition(){
        return a+b;
    }

    @GetMapping("/get-sub")
    public int subtraction(){
        return a-b;
    }

    @GetMapping("/get-div")
    public double division(){
        if(b==0){
            return 0;
        }
        return (double) a/b;
    }

    @GetMapping("/get-mul")
    public long multiplication(){
        return (long) a*b;
    }
}
