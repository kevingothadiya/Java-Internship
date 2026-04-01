package com.example.onetomanymapping.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "student_info")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_no")
    private String mobileno;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> address;
}
