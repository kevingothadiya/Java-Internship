package com.example.bloodmanagementproject.controller;

import com.example.bloodmanagementproject.proxy.BloodStockProxy;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;
import com.example.bloodmanagementproject.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/user")
    public ResponseEntity<List<UserProxy>> getUsers(){
        return new ResponseEntity<>(adminService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/paged")
    public ResponseEntity<?> getUsersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(adminService.getUsersPaged(page, size), HttpStatus.OK);
    }

    @GetMapping("/donation")
    public ResponseEntity<List<DonationProxy>> getDonationDetails(){
        return new ResponseEntity<>(adminService.getDonationDetails(),HttpStatus.OK);
    }

    @GetMapping("/blood-stock")
    public ResponseEntity<?> getBloodStock(){
        return new ResponseEntity<>(adminService.getBloodStock(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(adminService.deleteUser(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserProxy userProxy) {
        adminService.updateUser(id, userProxy);
        return ResponseEntity.ok("User updated successfully");
    }

    @PutMapping("/donor/{id}/approve")
    public ResponseEntity<String> ApproveDonationRequest(@PathVariable Long id){
        return new ResponseEntity<>(adminService.ApproveDonationRequest(id),HttpStatus.OK);
    }

    @PutMapping("/donor/{id}/reject")
    public ResponseEntity<String> RejectDonationRequest(@PathVariable Long id){
        return new ResponseEntity<>(adminService.RejectDonationRequest(id),HttpStatus.OK);
    }

    @PostMapping("/blood-stock/add")
    public ResponseEntity<String> addBloodStoke(@Valid @RequestBody BloodStockProxy bloodStockProxy){
        return new ResponseEntity<>(adminService.addBloodStoke(bloodStockProxy),HttpStatus.OK);
    }

    @PutMapping("/blood/request/{id}/approve")
    public ResponseEntity<String> ApproveBloodRequest(@PathVariable Long id){
        try {
            return new ResponseEntity<>(adminService.ApproveBloodRequest(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/blood/request/{id}/reject")
    public ResponseEntity<String> RejectBloodRequest(@PathVariable Long id){
        return new ResponseEntity<>(adminService.RejectBloodRequest(id),HttpStatus.OK);
    }

    @GetMapping("/donation/paged")
    public ResponseEntity<?> getDonationsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        return new ResponseEntity<>(adminService.getDonationsPaged(page, size), HttpStatus.OK);
    }

    @GetMapping("/blood/request/paged")
    public ResponseEntity<?> getBloodRequestsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        return new ResponseEntity<>(adminService.getBloodRequestsPaged(page, size), HttpStatus.OK);
    }

    @GetMapping("/excel/user/download")
    public ResponseEntity<byte[]> downloadUserExcel(){
//        byte[] bytes = adminService.downloadUserExcel();
//        String folderPath = "downloads";
//        File folder = new File(folderPath);
//        if(!folder.exists()){
//            folder.mkdirs();
//        }
//        String filename = "Users.xlsx";
//
//        Path path = Paths.get(folderPath , filename);
//
//        try{
//            Files.write(path , bytes);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//
//        return  new ResponseEntity<>("File saved at: " + path.toAbsolutePath(),
//                HttpStatus.OK);
        byte[] bytes = adminService.downloadUserExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("Users.xlsx")
                .build());
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
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
