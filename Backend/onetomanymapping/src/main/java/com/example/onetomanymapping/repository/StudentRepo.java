package com.example.onetomanymapping.repository;

import com.example.onetomanymapping.domain.Student;
import com.example.onetomanymapping.service.StudentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    Student findByEmailId(String email);
}
