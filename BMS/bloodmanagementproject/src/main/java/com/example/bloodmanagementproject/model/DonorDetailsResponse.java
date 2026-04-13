package com.example.bloodmanagementproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonorDetailsResponse {
    private Long id;
    private String bloodGrp;
    private Integer age;
    private String gender;
    private String city;
    private LocalDate lastDonationDate;
    private Boolean available;
}
