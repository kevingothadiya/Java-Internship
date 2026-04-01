package com.example.onttomanyfullproject.repository;

import com.example.onttomanyfullproject.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    @Transactional
    Optional<Student> deleteByEmailId(String emailId);

    Optional<Student> findByEmailId(String emailId);

    Optional<Student> findByUserName(String userName);
}
