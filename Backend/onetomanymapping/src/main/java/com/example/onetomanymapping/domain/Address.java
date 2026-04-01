package com.example.onetomanymapping.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "list_of_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String contry;
    private Integer pincode;

}
