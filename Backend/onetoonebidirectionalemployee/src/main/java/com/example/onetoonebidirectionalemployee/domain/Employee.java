package com.example.onetoonebidirectionalemployee.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_detail")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;
}
