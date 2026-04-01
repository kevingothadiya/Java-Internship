package com.example.globalexceptiondemo.proxy;

import lombok.Data;

@Data
public class AddressProxy {
    private Long id;
    private String street;
    private String city;
    private String country;
    private Long pincode;
    private StudentProxy student;
}
