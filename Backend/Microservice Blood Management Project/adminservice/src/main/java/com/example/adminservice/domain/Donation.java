package com.example.adminservice.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Donation {
    private Long id;
    private LocalDate donationDate;
    private Long quantity;
    private String remark;
    private DonorDetails donorDetails;
}
