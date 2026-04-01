package com.example.donorservice.repository;

import com.example.donorservice.domain.DonorDetails;
import com.example.donorservice.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorDetailsRepo extends JpaRepository<DonorDetails,Long> {

    Optional<DonorDetails> findByUserId(Long id);

    Optional<List<DonorDetails>> findByBloodGrp(String bloodGrp);
}
