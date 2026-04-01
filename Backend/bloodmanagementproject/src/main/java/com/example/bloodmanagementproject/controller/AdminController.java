package com.example.bloodmanagementproject.controller;

import com.example.bloodmanagementproject.proxy.BloodStockProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;
import com.example.bloodmanagementproject.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/user")
    public ResponseEntity<List<UserProxy>> getUsers(){
        return new ResponseEntity<>(adminService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/donor/{id}/approve")
    public ResponseEntity<String> ApproveDonationRequest(@PathVariable Long id){
        return new ResponseEntity<>(adminService.ApproveDonationRequest(id),HttpStatus.OK);
    }

    @PostMapping("/blood-stock/add")
    public ResponseEntity<String> addBloodStoke(@Valid @RequestBody BloodStockProxy bloodStockProxy){
        return new ResponseEntity<>(adminService.addBloodStoke(bloodStockProxy),HttpStatus.OK);
    }

    @PutMapping("/blood/request/{id}/approve")
    public ResponseEntity<String> ApproveBloodRequest(@PathVariable Long id){
        return new ResponseEntity<>(adminService.ApproveBloodRequest(id),HttpStatus.OK);
    }

    @GetMapping("/excel/user/download")
    public ResponseEntity<String> downloadUserExcel(){
        byte[] bytes = adminService.downloadUserExcel();
        String folderPath = "downloads";
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String filename = "Users.xlsx";

        Path path = Paths.get(folderPath , filename);

        try{
            Files.write(path , bytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return  new ResponseEntity<>("File saved at: " + path.toAbsolutePath(),
                HttpStatus.OK);
    }

    @GetMapping("/excel/hospital/download")
    public ResponseEntity<String> downloadHospitalExcel(){
        byte[] bytes = adminService.downloadHospitalExcel();
        String folderPath = "downloads";
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String filename = "Hospitals.xlsx";

        Path path = Paths.get(folderPath , filename);

        try{
            Files.write(path , bytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return  new ResponseEntity<>("File saved at: " + path.toAbsolutePath(),
                HttpStatus.OK);
    }

    @GetMapping("/excel/blood-stoke/download")
    public ResponseEntity<String> downloadBloodStokeExcel(){
        byte[] bytes = adminService.downloadBloodStokeExcel();
        String folderPath = "downloads";
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String filename = "Blood_Stoke.xlsx";

        Path path = Paths.get(folderPath , filename);

        try{
            Files.write(path , bytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return  new ResponseEntity<>("File saved at: " + path.toAbsolutePath(),
                HttpStatus.OK);
    }

    @GetMapping("/excel/donor-details/download")
    public ResponseEntity<String> downloadDonorDetailsExcel(){
        byte[] bytes = adminService.downloadDonorDetailsExcel();
        String folderPath = "downloads";
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String filename = "Donor_Details.xlsx";

        Path path = Paths.get(folderPath , filename);

        try{
            Files.write(path , bytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return  new ResponseEntity<>("File saved at: " + path.toAbsolutePath(),
                HttpStatus.OK);
    }
}
