package com.example.onetoonebidirectional.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String country;

    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
    private  Student student;
}
