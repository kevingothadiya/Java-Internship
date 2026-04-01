package com.example.jpastudentdemo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "general_information")
public class StudentDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_no")
    private String mobileNo;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonalInformation personalInformation;
}
