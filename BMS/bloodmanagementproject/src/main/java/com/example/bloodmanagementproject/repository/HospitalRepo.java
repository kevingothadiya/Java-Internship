package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.Hospital;
import com.example.bloodmanagementproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HospitalRepo extends JpaRepository<Hospital,Long> {

    Optional<Hospital> findByUsers(Users users);
}
