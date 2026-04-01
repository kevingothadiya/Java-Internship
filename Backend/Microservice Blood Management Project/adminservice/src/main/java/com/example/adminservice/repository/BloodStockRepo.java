package com.example.adminservice.repository;

import com.example.adminservice.domain.BloodStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodStockRepo extends JpaRepository<BloodStock,Long> {

    Optional<BloodStock> findByBloodGrp(String bloodGrp);
}

