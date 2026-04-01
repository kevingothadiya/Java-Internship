package com.example.hospitalservice.repository;

import com.example.hospitalservice.domain.BloodRequest;
import com.example.hospitalservice.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepo extends JpaRepository<BloodRequest,Long> {
    List<BloodRequest> findByHospital(Hospital hospital);
}
