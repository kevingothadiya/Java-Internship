package com.example.onetoonebidirectional.repository;

import com.example.onetoonebidirectional.domain.Student;
import com.example.onetoonebidirectional.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
