package com.example.bloodmanagementproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate donationDate;
    private Long quantity;
    private String remark;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id", nullable = false)
    private DonorDetails donorDetails;
}
