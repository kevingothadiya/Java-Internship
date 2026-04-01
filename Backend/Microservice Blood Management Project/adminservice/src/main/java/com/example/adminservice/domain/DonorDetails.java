package com.example.adminservice.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DonorDetails {
    private Long id;
    private String bloodGrp;
    private Integer age;
    private String gender;
    private String city;
    private LocalDate lastDonationDate;
    private Boolean available;
}
