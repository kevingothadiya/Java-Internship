package com.example.onttomanyfullproject.entity;

import com.example.onttomanyfullproject.ValidPassWord;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String passWord;
    private String branch;
    private String emailId;
    private String mobileNo;
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<Address> addresses = new ArrayList<>();
}
