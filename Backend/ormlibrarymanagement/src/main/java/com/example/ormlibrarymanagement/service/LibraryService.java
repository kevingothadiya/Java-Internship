package com.example.ormlibrarymanagement.service;

import com.example.ormlibrarymanagement.entity.LibraryEntity;

import java.util.List;

public interface LibraryService {

    String addBook(LibraryEntity lib);

    List<LibraryEntity> getAllBook();

    LibraryEntity getBookById(Long id);

    String updateBook(LibraryEntity lib,Long id);

    String deleteBookById(Long id);

    List<LibraryEntity> searchByTitle(String title);

    List<LibraryEntity> searchByAuthor(String author);

    Long countBook();

    List<LibraryEntity> sortByPrice();

    String bulkAdd(List<LibraryEntity> lib);

    String deleteAll();
}
