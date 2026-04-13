package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

    Page<Users> findByRoleNot(String role, Pageable pageable);
}
