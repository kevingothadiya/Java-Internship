package com.example.manytomanydemo.controller;

import com.example.manytomanydemo.dto.CourceDto;
import com.example.manytomanydemo.service.implementation.CourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cource")
public class CourceController {

    @Autowired
    private CourceServiceImpl courceService;

    @PostMapping("/save")
    public String saveAll(@RequestBody CourceDto courceDto){
        return courceService.saveAll(courceDto);
    }

    @GetMapping("/get-all")
    public List<CourceDto> getAll(){
        return courceService.getAll();
    }

    @GetMapping("/get-by-title")
    public List<CourceDto> getByCourceTitle(@RequestParam String title){
        return courceService.getByCourceTitle(title);
    }
}
