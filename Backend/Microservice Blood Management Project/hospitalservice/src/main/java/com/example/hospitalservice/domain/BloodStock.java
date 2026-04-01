package com.example.hospitalservice.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BloodStock {
    private Long id;
    private String bloodGrp;
    private Double unitsAvailable;
    private LocalDateTime lastUpdated;
}
