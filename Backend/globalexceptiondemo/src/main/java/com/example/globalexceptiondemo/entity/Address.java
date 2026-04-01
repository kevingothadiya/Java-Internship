package com.example.globalexceptiondemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String country;
    private Long pincode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
}
