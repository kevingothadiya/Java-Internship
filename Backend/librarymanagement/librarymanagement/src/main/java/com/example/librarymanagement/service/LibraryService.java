package com.example.librarymanagement.service;

import com.example.librarymanagement.model.LibraryManagement;

import java.util.List;

public interface LibraryService {
    String addBook(LibraryManagement l);

    List<LibraryManagement> getAllBooks();

    LibraryManagement getbookById(Long id);

    String updateBook(LibraryManagement l,Long id);

    String deleteBookById(Long id);

    LibraryManagement searchByTitle(String title);

    LibraryManagement searchByAuthor(String author);

    Long countBooks();

    List<LibraryManagement> sortByPrice();

    String bulkAdd(List<LibraryManagement> l);

    String deleteAll();
}
