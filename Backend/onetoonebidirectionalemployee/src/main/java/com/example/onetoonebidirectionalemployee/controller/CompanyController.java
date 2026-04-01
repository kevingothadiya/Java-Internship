package com.example.onetoonebidirectionalemployee.controller;

import com.example.onetoonebidirectionalemployee.dto.CompanyDto;
import com.example.onetoonebidirectionalemployee.service.implementation.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @PostMapping("/save")
    public ResponseEntity<String> saveComData(@RequestBody CompanyDto companyDto){
        String s = companyService.saveComData(companyDto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CompanyDto>> getAllComData(){
        List<CompanyDto> allComData = companyService.getAllComData();
        return new ResponseEntity<>(allComData,HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<CompanyDto> findComDataById(@PathVariable Long id){
        CompanyDto comDataById = companyService.findComDataById(id);
        return new ResponseEntity<>(comDataById,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateCom(@RequestBody CompanyDto companyDto,@PathVariable Long id){
        String s = companyService.updateCom(companyDto, id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @GetMapping("/remove-by-id/{id}")
    public ResponseEntity<String> removeComDataById(@PathVariable Long id) {
        String s = companyService.removeComDataById(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @GetMapping("/remove")
    public ResponseEntity<String> removeAllCompanyData()  {
        String s = companyService.removeAllCompanyData();
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
