package com.example.donorservice.proxy;

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
    private DonorDetailsProxy donorDetails;

}
