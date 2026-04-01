package com.example.onetoonebidirectional.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student_data")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
