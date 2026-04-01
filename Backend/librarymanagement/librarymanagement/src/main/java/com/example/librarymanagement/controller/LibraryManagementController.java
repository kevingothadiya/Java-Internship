package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.LibraryManagement;
import com.example.librarymanagement.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryManagementController {

    @Autowired
    private LibraryService library;

    @PostMapping("/add-book")
    public String addBook(@RequestBody LibraryManagement l){
        return library.addBook(l);
    }

    @GetMapping("/get-all-books")
    public List<LibraryManagement> getAllBooks(){
        return  library.getAllBooks();
    }

    @GetMapping("get-book-by-id/{id}")
    public LibraryManagement getBookById(@PathVariable Long id){
        return library.getbookById(id);
    }

    @PutMapping("update-book/{id}")
    public String updatebook(@RequestBody LibraryManagement l,@PathVariable Long id){
        return library.updateBook(l,id);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public String deleteBookById(@PathVariable Long id){
        return library.deleteBookById(id);
    }

    @GetMapping("search-by-title/{title}")
    public LibraryManagement searchByTitle(@PathVariable String title){
        return library.searchByTitle(title);
    }

    @GetMapping("/search-by-author/{author}")
    public LibraryManagement searchByAuthor(@PathVariable String author){
        return library.searchByAuthor(author);
    }

    @GetMapping("/count")
    public Long count(){
        return library.countBooks();
    }

    @GetMapping("/sort-by-price")
    public List<LibraryManagement> sortByPrice(){
        return library.sortByPrice();
    }

    @PostMapping("/bulk-add")
    public String bulkAdd(@RequestBody List<LibraryManagement> l){
        return library.bulkAdd(l);
    }

    @DeleteMapping("/delete-all")
    public String deleteAll(){
        return library.deleteAll();
    }
}
