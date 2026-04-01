package com.example.onetoonebidirectional.controller;

import com.example.onetoonebidirectional.dto.AddressDto;
import com.example.onetoonebidirectional.service.implementation.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/save-by-address")
    public String saveAddressData(@RequestBody AddressDto addressDto){
        return addressService.saveAddressData(addressDto);
    }

    @GetMapping("/get-all-address-data")
    public List<AddressDto> getAllAddressData(){
        return addressService.getAllAddressData();
    }

    @GetMapping("/get-address-data-by-id/{id}")
    public AddressDto getAddrssById(@PathVariable Long id){
        return addressService.getAddrssById(id);
    }
}
