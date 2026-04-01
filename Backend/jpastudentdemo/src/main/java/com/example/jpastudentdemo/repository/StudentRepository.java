package com.example.jpastudentdemo.repository;

import com.example.jpastudentdemo.domain.StudentDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentDomain,Long> {
    StudentDomain findByEmailId(String emailid);
}
