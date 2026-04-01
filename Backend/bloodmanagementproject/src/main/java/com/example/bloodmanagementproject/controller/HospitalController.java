package com.example.bloodmanagementproject.controller;

import com.example.bloodmanagementproject.model.HospitalBloodRequestHistory;
import com.example.bloodmanagementproject.proxy.BloodRequestProxy;
import com.example.bloodmanagementproject.proxy.HospitalProxy;
import com.example.bloodmanagementproject.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/add")
    public ResponseEntity<String> addHospital(@Valid @RequestBody HospitalProxy hospitalProxy) {
        return new ResponseEntity<>(hospitalService.addHospital(hospitalProxy), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<HospitalProxy> getHospitalDetails(@PathVariable Long id) {
        return new ResponseEntity<>(hospitalService.getHospitalDetails(id), HttpStatus.OK);
    }

    @PostMapping("/request")
    public ResponseEntity<String> generateRequestForBlood(@Valid @RequestBody BloodRequestProxy bloodRequestProxy) {
        return new ResponseEntity<>(hospitalService.generateRequestForBlood(bloodRequestProxy), HttpStatus.OK);
    }

    @GetMapping("/request/history/{id}")
    public ResponseEntity<List<HospitalBloodRequestHistory>> getBloodRequestHistory(@PathVariable Long id){
        return new ResponseEntity<>(hospitalService.getBloodRequestHistory(id),HttpStatus.OK);
    }
}