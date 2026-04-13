package com.example.bloodmanagementproject.controller;

import com.example.bloodmanagementproject.proxy.DonorDetailsProxy;
import com.example.bloodmanagementproject.proxy.HospitalProxy;
import com.example.bloodmanagementproject.service.UserService;
import com.example.bloodmanagementproject.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search/blood/{bloodGrp}")
    public ResponseEntity<List<DonorDetailsProxy>> findUserByBloodGroup(@PathVariable String bloodGrp) {
        return new ResponseEntity<>(userService.findUserByBloodGroup(bloodGrp), HttpStatus.OK);
    }

    @GetMapping("/search/donors")
    public ResponseEntity<List<DonorDetailsProxy>> getAllDoners() {
        return new ResponseEntity<>(userService.getAllDoners(), HttpStatus.OK);
    }

    @GetMapping("/hospitals")
    public ResponseEntity<List<HospitalProxy>> getHospital(){
        return new ResponseEntity<>(userService.getHospital(),HttpStatus.OK);
    }
}