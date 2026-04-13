package com.example.bloodmanagementproject.proxy;

import com.example.bloodmanagementproject.domain.DonorDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationProxy {
    private Long id;
    private LocalDate donationDate;
    private Long quantity;
    private String remark;
    private DonorDetails donorDetails;
}
