package com.example.globalexceptiondemo.controller;

import com.example.globalexceptiondemo.proxy.AddressProxy;
import com.example.globalexceptiondemo.service.implementation.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/save")
    public ResponseEntity<String> saveAddress(@RequestBody AddressProxy addressProxy){
        String s = addressService.saveAddress(addressProxy);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AddressProxy>> getAllAddressData(){
        List<AddressProxy> allAddressData = addressService.getAllAddressData();
        return new ResponseEntity<List<AddressProxy>>(allAddressData,HttpStatus.OK);
    }

    @GetMapping("/get-by-city/{city}")
    public ResponseEntity<List<AddressProxy>> findAddressByCity(@PathVariable String city){
        List<AddressProxy> addressByCity = addressService.findAddressByCity(city);
        return new ResponseEntity<>(addressByCity,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateAddressData(@RequestBody AddressProxy addressProxy,@PathVariable Long id){
        String s = addressService.updateAddressData(addressProxy, id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> removeById(@PathVariable Long id){
        String s = addressService.removeById(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
