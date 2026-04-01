package com.example.imagedemo.repository;

import com.example.imagedemo.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileStorageRepo extends JpaRepository<FileStorage,Long> {

    Optional<FileStorage> findByDockId(String dockId);
}
