package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.model.DonationDetailsHistory;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.DonorDetailsProxy;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DonorService {

    List<DonorDetailsProxy> getDonorDetails();

    String updateDonorDetails(DonorDetailsProxy donorDetailsProxy);

    @Transactional
    String donateBlood(DonationProxy donationProxy);

    List<DonationDetailsHistory> getDonorHistory(Long id);
}
