package com.example.manytomanydemo.repository;

import com.example.manytomanydemo.dto.CourceDto;
import com.example.manytomanydemo.entity.Cource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourceRepo extends JpaRepository<Cource,Long> {

    public List<Cource> findByTitle(String title);
}
