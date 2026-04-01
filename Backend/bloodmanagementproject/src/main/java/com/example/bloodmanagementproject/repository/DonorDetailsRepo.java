package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.DonorDetails;
import com.example.bloodmanagementproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorDetailsRepo extends JpaRepository<DonorDetails,Long> {

    Optional<DonorDetails> findByUsers(Users users);

    Optional<List<DonorDetails>> findByBloodGrp(String bloodGrp);
}
