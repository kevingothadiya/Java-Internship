package com.example.ormstudentdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class StudentEntity {
    @Id
    private Long id;
    private String name;
    private String department;
    private Double cgpa;
}
