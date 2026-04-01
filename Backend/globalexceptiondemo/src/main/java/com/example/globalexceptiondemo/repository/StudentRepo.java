package com.example.globalexceptiondemo.repository;

import com.example.globalexceptiondemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {

    public List<Student> findByName(String name);
}
