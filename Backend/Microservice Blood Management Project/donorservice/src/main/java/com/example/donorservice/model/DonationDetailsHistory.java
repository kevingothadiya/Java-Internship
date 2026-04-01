package com.example.donorservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DonationDetailsHistory {
    private Long id;
    private LocalDate donationDate;
    private Long quantity;
    private String remark;
}
