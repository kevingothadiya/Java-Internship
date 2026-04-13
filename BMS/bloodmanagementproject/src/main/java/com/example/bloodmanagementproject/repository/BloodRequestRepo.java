package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.BloodRequest;
import com.example.bloodmanagementproject.domain.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepo extends JpaRepository<BloodRequest,Long> {
    List<BloodRequest> findByHospital(Hospital hospital);
    Page<BloodRequest> findByHospitalOrderByRequestDateDesc(Hospital hospital, Pageable pageable);
}
