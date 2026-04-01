package com.example.ormlibrarymanagement.controller;

import com.example.ormlibrarymanagement.entity.LibraryEntity;
import com.example.ormlibrarymanagement.service.implementation.LibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController {

    @Autowired
    private LibraryServiceImpl book;

    @PostMapping("/add-book")
    public String addBook(@RequestBody LibraryEntity lib){
        return book.addBook(lib);
    }

    @GetMapping("/get-all-book")
    public List<LibraryEntity> getAllBook(){
        return book.getAllBook();
    }

    @GetMapping("/get-book-by-id/{id}")
    public LibraryEntity getBookById(@PathVariable Long id){
        return book.getBookById(id);
    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@RequestBody LibraryEntity lib,@PathVariable Long id){
        return book.updateBook(lib,id);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public String deleteBookById(@PathVariable Long id){
        return book.deleteBookById(id);
    }

    @GetMapping("/search-by-title/{title}")
    public List<LibraryEntity> searchByTitle(@PathVariable String title){
        return book.searchByTitle(title);
    }

    @GetMapping("/search-by-author/{author}")
    public List<LibraryEntity> searchByAuthor(@PathVariable String author){
        return book.searchByAuthor(author);
    }

    @GetMapping("/count-book")
    public Long countBook(){
        return book.countBook();
    }

    @GetMapping("/sort-by-price")
    public List<LibraryEntity> sortByPrice(){
        return book.sortByPrice();
    }

    @PostMapping("/bulk-add")
    public String bulkAdd(@RequestBody List<LibraryEntity> lib){
        return book.bulkAdd(lib);
    }

    @DeleteMapping("/delete-book")
    public String deleteAll(){
        return book.deleteAll();
    }
}
