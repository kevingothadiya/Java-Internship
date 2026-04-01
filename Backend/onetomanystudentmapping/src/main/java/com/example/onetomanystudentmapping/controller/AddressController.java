package com.example.onetomanystudentmapping.controller;

import com.example.onetomanystudentmapping.dto.AddressDto;
import com.example.onetomanystudentmapping.service.implementation.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/save")
    public String saveAddData(@RequestBody AddressDto addressDto){
        return addressService.saveAddData(addressDto);
    }

    @GetMapping("/get-all")
    public List<AddressDto> getAllAddData(){
        return addressService.getAllAddData();
    }

    @GetMapping("/get-by-id/{id}")
    public AddressDto getAddDataById(@PathVariable Long id){
        return addressService.getAddDataById(id);
    }

    @PostMapping("/update/{id}")
    public String updateAddData(@RequestBody AddressDto addressDto,@PathVariable Long id){
        return addressService.updateAddData(addressDto,id);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public String removeById(@PathVariable Long id){
        return addressService.removeById(id);
    }
}
