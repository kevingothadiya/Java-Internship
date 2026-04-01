package com.example.adminservice.controller;

import com.example.adminservice.domain.BloodStock;
import com.example.adminservice.domain.Users;
import com.example.adminservice.proxy.BloodStockProxy;
import com.example.adminservice.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<Users>> getUsers(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(adminService.getUsers(header), HttpStatus.OK);
    }

    @PutMapping("/donation/{id}/approve")
    public ResponseEntity<String> ApproveDonationRequest(@PathVariable Long id,HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(adminService.ApproveDonationRequest(id,header),HttpStatus.OK);
    }

    @PostMapping("/blood-stock/add")
    public ResponseEntity<String> addBloodStoke(@Valid @RequestBody BloodStockProxy bloodStockProxy,HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(adminService.addBloodStoke(bloodStockProxy,header),HttpStatus.OK);
    }

    @PutMapping("/blood/request/{id}/approve")
    public ResponseEntity<String> ApproveBloodRequest(@PathVariable Long id,HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return new ResponseEntity<>(adminService.ApproveBloodRequest(id,header),HttpStatus.OK);
    }

    @GetMapping("/excel/user/download")
    public ResponseEntity<String> downloadUserExcel(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        byte[] bytes = adminService.downloadUserExcel(header);
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

    @GetMapping("/excel/hos/download")
    public ResponseEntity<String> downloadHospitalExcel(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        byte[] bytes = adminService.downloadHospitalExcel(header);
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
    public ResponseEntity<String> downloadBloodStokeExcel(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        byte[] bytes = adminService.downloadBloodStokeExcel(header);
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

    @GetMapping("/excel/don-details/download")
    public ResponseEntity<String> downloadDonorDetailsExcel(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        byte[] bytes = adminService.downloadDonorDetailsExcel(header);
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

    @GetMapping("/find-by-blood/{bloodGrp}")
    public ResponseEntity<BloodStock> getBloodStokeByBlood(@PathVariable String bloodGrp){
        return new ResponseEntity<>(adminService.getBloodStokeByBlood(bloodGrp),HttpStatus.OK);
    }
}
