package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.BloodStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodStockRepo extends JpaRepository<BloodStock,Long> {

    Optional<BloodStock> findByBloodGrp(String bloodGrp);
}
