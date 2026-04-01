package com.example.donorservice.controller;

import com.example.donorservice.domain.Donation;
import com.example.donorservice.domain.DonorDetails;
import com.example.donorservice.model.DonationDetailsHistory;
import com.example.donorservice.proxy.DonationProxy;
import com.example.donorservice.proxy.DonorDetailsProxy;
import com.example.donorservice.service.DonorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.experimental.PackagePrivate;
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
    public ResponseEntity<String> updateDonorDetails(@Valid @RequestBody DonorDetailsProxy donorDetailsProxy, HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(donorService.updateDonorDetails(donorDetailsProxy,header),HttpStatus.OK);
    }

    @PostMapping("/donate")
    public ResponseEntity<String> donateBlood( @RequestBody DonationProxy donationProxy,HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(donorService.donateBlood(donationProxy,header),HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<DonationDetailsHistory>> getDonorHistory(@PathVariable Long id,HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(donorService.getDonorHistory(id,header),HttpStatus.OK);
    }

    @GetMapping("/get-donation-details/{id}")
    public Donation getDonationById(@PathVariable Long id){
        return donorService.getDonationById(id);
    }

    @PostMapping("/save")
    public String SaveDonation(@RequestBody Donation donation){
        return donorService.SaveDonation(donation);
    }

    @GetMapping("/get-all-donor-details")
    public List<DonorDetails> getDonorDetailsFa(){
        return donorService.getDonorDetailsFa();
    }
}
