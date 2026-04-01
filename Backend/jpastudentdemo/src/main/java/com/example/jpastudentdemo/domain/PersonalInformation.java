package com.example.jpastudentdemo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "personal_information")
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @Column(name = "acc_no")
    private String accno;
}
