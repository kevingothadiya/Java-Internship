package com.example.securitydemo.repository;

import com.example.securitydemo.dto.StudentDto;
import com.example.securitydemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    Optional<Student> findByUserName(String username);
}
