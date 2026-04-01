package com.example.manytomanydemo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String modules;
    private Double fees;

    @ManyToMany(mappedBy = "cources", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Student> students;
}
