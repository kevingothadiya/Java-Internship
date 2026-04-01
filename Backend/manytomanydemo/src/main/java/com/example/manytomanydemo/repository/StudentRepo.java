package com.example.manytomanydemo.repository;

import com.example.manytomanydemo.dto.StudentDto;
import com.example.manytomanydemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    public List<Student> findByDepartment(String department);

    public List<Student> findByName(String name);
}
