package com.example.hospitalservice.repository;

import com.example.hospitalservice.domain.Hospital;
import com.example.hospitalservice.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepo extends JpaRepository<Hospital,Long> {

    Optional<Users> findByUserId(Long id);
}
