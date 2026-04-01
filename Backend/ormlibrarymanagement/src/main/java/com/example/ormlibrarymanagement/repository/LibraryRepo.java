package com.example.ormlibrarymanagement.repository;

import com.example.ormlibrarymanagement.entity.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<LibraryEntity,Long> {
    List<LibraryEntity> findByTitle(String title);

    List<LibraryEntity> findByAuthor(String author);
}
