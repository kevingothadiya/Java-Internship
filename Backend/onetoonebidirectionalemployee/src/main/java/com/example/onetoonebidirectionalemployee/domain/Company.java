package com.example.onetoonebidirectionalemployee.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "company_details")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comName;
    private String comAddress;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private Employee employee;
}
