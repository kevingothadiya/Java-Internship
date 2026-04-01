package com.example.hospitalservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HospitalBloodRequestHistory {

    private Long id;
    private String bloodGrp;
    private Long quantity;
    private LocalDate requestDate;
    private String status;
}
