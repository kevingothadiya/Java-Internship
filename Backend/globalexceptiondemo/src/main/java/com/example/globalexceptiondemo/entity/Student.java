package com.example.globalexceptiondemo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String branch;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<Address> address = new ArrayList<>();

}
