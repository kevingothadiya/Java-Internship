package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.Donation;
import com.example.bloodmanagementproject.domain.DonorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepo extends JpaRepository<Donation,Long> {

    Optional<List<Donation>> findByDonorDetails(DonorDetails donorDetails);
}
