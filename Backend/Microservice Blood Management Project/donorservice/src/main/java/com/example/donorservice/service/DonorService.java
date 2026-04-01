package com.example.donorservice.service;

import com.example.donorservice.domain.Donation;
import com.example.donorservice.domain.DonorDetails;
import com.example.donorservice.model.DonationDetailsHistory;
import com.example.donorservice.proxy.DonationProxy;
import com.example.donorservice.proxy.DonorDetailsProxy;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DonorService {
    List<DonorDetailsProxy> getDonorDetails();

    String updateDonorDetails(DonorDetailsProxy donorDetailsProxy,String token);

    @Transactional
    String donateBlood(DonationProxy donationProxy,String token);

    List<DonationDetailsHistory> getDonorHistory(Long id,String token);

    Donation getDonationById(Long id);

    String SaveDonation(Donation donation);

    List<DonorDetails> getDonorDetailsFa();
}
