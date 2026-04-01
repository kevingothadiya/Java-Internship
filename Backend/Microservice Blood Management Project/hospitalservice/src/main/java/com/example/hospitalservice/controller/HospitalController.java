package com.example.hospitalservice.controller;

import com.example.hospitalservice.domain.BloodRequest;
import com.example.hospitalservice.domain.Hospital;
import com.example.hospitalservice.model.HospitalBloodRequestHistory;
import com.example.hospitalservice.proxy.BloodRequestProxy;
import com.example.hospitalservice.proxy.HospitalProxy;
import com.example.hospitalservice.service.HospitalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
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
    public ResponseEntity<String> addHospital(@Valid @RequestBody HospitalProxy hospitalProxy, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(hospitalService.addHospital(hospitalProxy,header), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<HospitalProxy> getHospitalDetails(@PathVariable Long id,HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(hospitalService.getHospitalDetails(id,header), HttpStatus.OK);
    }

    @PostMapping("/request")
    public ResponseEntity<String> generateRequestForBlood(@Valid @RequestBody BloodRequestProxy bloodRequestProxy,HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(hospitalService.generateRequestForBlood(bloodRequestProxy,header), HttpStatus.OK);
    }

    @GetMapping("/request/history/{id}")
    public ResponseEntity<List<HospitalBloodRequestHistory>> getBloodRequestHistory(@PathVariable Long id,HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(hospitalService.getBloodRequestHistory(id,header),HttpStatus.OK);
    }

    @GetMapping("/get-blood-request/{id}")
    public BloodRequest getBloodRequestById(@PathVariable Long id){
        return hospitalService.getBloodRequestById(id);
    }

    @PostMapping("/blood-request/save")
    public String saveBloodRequest(@RequestBody BloodRequest bloodRequest){
        return hospitalService.saveBloodRequest(bloodRequest);
    }

    @GetMapping("/get-all-hospital")
    public List<Hospital> getAllHospital(){
        return hospitalService.getAllHospital();
    }
}
