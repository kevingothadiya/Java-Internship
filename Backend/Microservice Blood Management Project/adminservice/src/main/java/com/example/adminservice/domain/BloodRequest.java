package com.example.adminservice.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BloodRequest {
    private Long id;
    private String bloodGrp;
    private Long quantity;
    private LocalDate requestDate;
    private String status;

    private Hospital hospital;

}
