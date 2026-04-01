package com.example.bloodmanagementproject.controller;

import com.example.bloodmanagementproject.model.DonationDetailsHistory;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.DonorDetailsProxy;
import com.example.bloodmanagementproject.service.DonorService;
import com.example.bloodmanagementproject.service.implementation.DonorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @GetMapping("/profile")
    public ResponseEntity<List<DonorDetailsProxy>> getDonorDetails(){
        return new ResponseEntity<>(donorService.getDonorDetails(), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateDonorDetails(@Valid @RequestBody DonorDetailsProxy donorDetailsProxy){
        return new ResponseEntity<>(donorService.updateDonorDetails(donorDetailsProxy),HttpStatus.OK);
    }

    @PostMapping("/donate")
    public ResponseEntity<String> donateBlood( @RequestBody DonationProxy donationProxy){
        return new ResponseEntity<>(donorService.donateBlood(donationProxy),HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<DonationDetailsHistory>> getDonorHistory(@PathVariable Long id){
        return new ResponseEntity<>(donorService.getDonorHistory(id),HttpStatus.OK);
    }
}
