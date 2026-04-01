package com.example.globalexceptiondemo.proxy;

import lombok.Data;

import java.util.List;

@Data
public class StudentProxy {

    private Long id;
    private String name;
    private String email;
    private String branch;
    private List<AddressProxy> address;
}
