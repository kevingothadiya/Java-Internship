package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HospitalRepo extends JpaRepository<Hospital,Long> {

}
